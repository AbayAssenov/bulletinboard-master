package kz.epam.training.bulletin_board.command.impl.handler;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.FormConstant;
import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.constant.MessageConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.dao.impl.UserDao;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.model.user.User;
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

public class Registration implements ActionCommand {

    private final Logger LOGGER = Logger.getLogger(Registration.class);

    private User user;

    private void createUser(HttpServletRequest request) {

        String name = request.getParameter(ParameterConstant.NAME);
        String email = request.getParameter(ParameterConstant.EMAIL);
        String password = DigestUtils.md5Hex(request.getParameter(ParameterConstant.PASSWORD).trim()); //Hashing of password

        user = new User(name, email, password);
    }

    private boolean checkEmailUser(User user, Connection connection) throws SQLException {

        boolean checkUserEmail;//Check user email variable

        UserDao userDao = new UserDao(connection);

        checkUserEmail = userDao.findEmail(user);// Init check var

        return checkUserEmail;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty(FormConstant.FORM_REGISTRATION);

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        try {
            connection.setAutoCommit(false);

            UserDao userDAO = new UserDao(connection);

            createUser(request);

            if (!checkEmailUser(user, connection)) {

                userDAO.create(user);

                connection.commit();

                page = ConfigurationManager.getProperty(FormConstant.FORM_LOGIN);
            } else {

                request.setAttribute(MessageConstant.ERROR_EMAIL, MessageManager.getProperty(MessageConstant.MESSAGE_ERROR_EMAIL));

                page = ConfigurationManager.getProperty(FormConstant.FORM_REGISTRATION);
            }

        } catch (SQLException b) {

            try {

                connection.rollback();

            } catch (SQLException e) {

                LOGGER.error(LoggerConstant.ERROR_REGISTRATION, e);
            }
            LOGGER.error(LoggerConstant.ERROR_REGISTRATION, b);

        } finally {

            pool.freeConnection(connection);

        }
        return page;
    }
}
