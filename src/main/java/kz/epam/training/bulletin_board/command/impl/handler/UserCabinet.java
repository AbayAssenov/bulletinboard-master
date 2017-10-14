package kz.epam.training.bulletin_board.command.impl.handler;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.command.impl.form.UserCabinetForm;
import kz.epam.training.bulletin_board.constant.*;
import kz.epam.training.bulletin_board.dao.impl.AdvertDao;
import kz.epam.training.bulletin_board.dao.impl.UserDao;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.exception.PhotoException;
import kz.epam.training.bulletin_board.model.user.PhotoUser;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import kz.epam.training.bulletin_board.resource.MessageManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Abay Assenov
 */

public class UserCabinet implements ActionCommand {

    private final Logger LOGGER = Logger.getLogger(UserCabinet.class);

    /**
     * Sets user's photo
     */
    private void setUserPhoto(HttpServletRequest request, Connection connection) throws PhotoException{

        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID);

        try {

            Part filePart = request.getPart(ParameterConstant.USER_PHOTO);
            InputStream inputStreamFile = filePart.getInputStream(); // input stream of the upload file
            PhotoUser newPhotoUser = new PhotoUser(userId, inputStreamFile);

            UserDao userDao = new UserDao(connection);
            userDao.updatePhoto(newPhotoUser);

        } catch (IOException | ServletException | SQLException e) {

            throw new PhotoException(LoggerConstant.ERROR_TRY_CREATE_PHOTO_USER,e);
        }
    }

    /**
     * Checks current password in database
     */
    private boolean checkUserPassword(HttpServletRequest request, Connection connection) throws SQLException{

        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID);
        String currentPassword = DigestUtils.md5Hex(request.getParameter(ParameterConstant.PASSWORD_CURRENT).trim());//Hashing of password

        UserDao userDao = new UserDao(connection);

        return userDao.readUserPassword(userId).equals(currentPassword);
    }

    /**
     * Changes password
     */
    private void changePassword(HttpServletRequest request, Connection connection) throws SQLException {

        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID);

        if (checkUserPassword(request, connection)) {

            String password = DigestUtils.md5Hex(request.getParameter(ParameterConstant.NEW_PASSWORD).trim()); //Hashing of password

            UserDao userDao = new UserDao(connection);
            userDao.updatePassword(userId, password);

            request.setAttribute(MessageConstant.MESSAGE_CHANGE_PASSWORD, MessageManager.getProperty(MessageConstant.MESSAGE_PASSWORD_SUCCESS));

        } else {
            request.setAttribute(MessageConstant.MESSAGE_CHANGE_PASSWORD, MessageManager.getProperty(MessageConstant.MESSAGE_PASSWORD_ERROR));
        }
    }


    /**
     * Sets advert in archive
     */
    private void setIsArchive(HttpServletRequest request, Connection connection) throws SQLException {

        int advertId = Integer.valueOf(request.getParameter(ParameterConstant.ID_ADVERT));
        boolean statusIsArchive = request.getParameter(ParameterConstant.IS_ARCHIVE) != null; //this is need for check checkbox

        AdvertDao advertDao = new AdvertDao(connection);
        advertDao.updateArchive(advertId, statusIsArchive);
    }


    /**
     * Checks that user is owner of advert
     */
    private boolean checkUserAdvert(HttpServletRequest request, Connection connection) throws SQLException {

        int advertId = Integer.valueOf(request.getParameter(ParameterConstant.ID_ADVERT));
        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID);

        AdvertDao advertDao = new AdvertDao(connection);

        return userId == advertDao.readUserId(advertId);
    }

    /**
     * Deletes advert in data base
     */
    private void removeAdvert(Integer advertId, Connection connection) throws SQLException {

        AdvertDao advertDao = new AdvertDao(connection);
        advertDao.delete(advertId);
    }

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty(FormConstant.FORM_MAIN);

        if (RoleConstant.USER.equals(request.getSession().getAttribute(RoleConstant.ROLE))) {

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            try {
                connection.setAutoCommit(false);

                String action = request.getParameter(CabinetConstant.ACTION);

                switch (action) {//Define command

                    case CabinetConstant.REMOVE_AD: { //Remove advert

                        if (checkUserAdvert(request, connection)) {
                            int advertId = Integer.valueOf(request.getParameter(ParameterConstant.ID_ADVERT));
                            removeAdvert(advertId, connection);
                        }
                    }
                    break;

                    case CabinetConstant.SET_PHOTO_USER:
                        setUserPhoto(request, connection);
                        break;
                    case CabinetConstant.CHANGE_PASSWORD:
                        changePassword(request, connection);
                        break;
                    case CabinetConstant.SET_AD_IS_ARCHIVE:
                        setIsArchive(request, connection);
                        break;
                }

                connection.commit();

                UserCabinetForm userCabinetForm = new UserCabinetForm(); //Forward to user cabinet after execute command
                page = userCabinetForm.execute(request); // Forward to cabinet

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
