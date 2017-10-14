package kz.epam.training.bulletin_board.command.impl.handler;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.command.impl.form.AdminCabinetForm;
import kz.epam.training.bulletin_board.constant.*;
import kz.epam.training.bulletin_board.dao.impl.AdvertDao;
import kz.epam.training.bulletin_board.dao.impl.UserDao;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import kz.epam.training.bulletin_board.resource.MessageManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Abay Assenov
 */

public class AdminCabinet implements ActionCommand {


    private final Logger LOGGER = Logger.getLogger(AdminCabinet.class);

    /**
     * Deletes advert in data base
     */
    private void removeAdvert(Integer advertId, Connection connection) throws SQLException {

        AdvertDao advertDao = new AdvertDao(connection);
        advertDao.delete(advertId);
    }

    /**
     * Sets item "admin check" value true
     */
    private void setCheck(Integer advertId, Connection connection) throws SQLException {

        AdvertDao advertDao = new AdvertDao(connection);
        advertDao.updateCheckAdmin(advertId, true);
    }

    /**
     * Sets item "admin check" value false
     */
    private void setBlock(Integer advertId, Connection connection) throws SQLException {

        AdvertDao advertDao = new AdvertDao(connection);
        advertDao.updateCheckAdmin(advertId, false);
    }

    /**
     * Changes password
     */
    private void changePassword(HttpServletRequest request, Connection connection) throws SQLException {

        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID);

        if (checkUserPassword(request, connection)) {

            request.setAttribute(MessageConstant.MESSAGE_CHANGE_PASSWORD, MessageManager.getProperty(MessageConstant.MESSAGE_PASSWORD_SUCCESS));

            String password = DigestUtils.md5Hex(request.getParameter(ParameterConstant.NEW_PASSWORD).trim()); //Hashing of password

            UserDao userDao = new UserDao(connection);
            userDao.updatePassword(userId, password);

        } else {
            request.setAttribute(MessageConstant.MESSAGE_CHANGE_PASSWORD, MessageManager.getProperty(MessageConstant.MESSAGE_PASSWORD_ERROR));
        }
    }

    /**
     * Checks current password in database
     */
    private boolean checkUserPassword(HttpServletRequest request, Connection connection) throws SQLException {

        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID);
        String currentPassword = DigestUtils.md5Hex(request.getParameter(ParameterConstant.PASSWORD_CURRENT).trim());//Hashing of password

        UserDao userDao = new UserDao(connection);

        return userDao.readUserPassword(userId).equals(currentPassword);
    }

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty(FormConstant.FORM_MAIN);

        if (RoleConstant.ADMIN.equals(request.getSession().getAttribute(RoleConstant.ROLE))) {  //Checking by id and role_user

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            try {

                connection.setAutoCommit(false);

                String action = request.getParameter(CabinetConstant.ACTION);

                switch (action) { //Define command
                    case CabinetConstant.REMOVE_AD: {

                        int advertId = Integer.valueOf(request.getParameter(ParameterConstant.ID_ADVERT));
                        removeAdvert(advertId, connection);
                    }
                    break;
                    case CabinetConstant.CHECKED_AD: {

                        int advertId = Integer.valueOf(request.getParameter(ParameterConstant.ID_ADVERT));
                        setCheck(advertId, connection);
                    }
                    break;
                    case CabinetConstant.UNCHECKED_AD: {

                        int advertId = Integer.valueOf(request.getParameter(ParameterConstant.ID_ADVERT));
                        setBlock(advertId, connection);
                    }
                    break;
                    case CabinetConstant.CHANGE_PASSWORD:
                        changePassword(request, connection);
                        break;
                }

                connection.commit();

                AdminCabinetForm adminCabinetForm = new AdminCabinetForm(); //Forward to admin cabinet after execute command
                page = adminCabinetForm.execute(request);  // Forward to cabinet

            } catch (SQLException b) {

                try {

                    connection.rollback();

                } catch (SQLException e) {

                    LOGGER.error(LoggerConstant.ERROR_HANDLER_CABINET, e);
                }

                LOGGER.error(LoggerConstant.ERROR_HANDLER_CABINET, b);

            } finally {

                pool.freeConnection(connection);
            }
        }
        return page;
    }
}
