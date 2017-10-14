package kz.epam.training.bulletin_board.command.impl.handler;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.*;
import kz.epam.training.bulletin_board.dao.impl.UserDao;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.model.user.User;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import kz.epam.training.bulletin_board.resource.MessageManager;
import kz.epam.training.bulletin_board.util.UserRole;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Abay Assenov
 */

public class Login implements ActionCommand {

    private final Logger LOGGER = Logger.getLogger(Login.class);

    /**
     * Extract login and password from request
     */
    private User currentUser(HttpServletRequest request) {

        String userEmail = request.getParameter(ParameterConstant.LOGIN);

        String userPassword = DigestUtils.md5Hex(request.getParameter(ParameterConstant.PASSWORD).trim()); //Hashing of password

        return new User(userEmail, userPassword);
    }

    /**
     * Checks login and password in data base
     */
    private boolean checkLogin(User user, Connection connection) throws SQLException{

        boolean checkUser;//Check user variable

        UserDao userDao = new UserDao(connection);

        checkUser = userDao.find(user);// Init check var

        return checkUser;
    }


    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty(FormConstant.FORM_LOGIN);

        ConnectionPool connectionPool = ConnectionPool.getInstance(); //Take connection
        Connection connection = connectionPool.getConnection();

        User user = currentUser(request);

        try {

            if (checkLogin(user, connection)) {

                HttpSession session = request.getSession();

                session.setAttribute(RoleConstant.ROLE, UserRole.getUserRole(user.getUserRole())); //Set user role
                session.setAttribute(ParameterConstant.USER_NAME, user.getUserName()); //Set user name
                session.setAttribute(ParameterConstant.USER_ID, user.getUserId()); //Set user id

                page = ConfigurationManager.getProperty(FormConstant.FORM_MAIN);
            } else {

                request.setAttribute(MessageConstant.MESSAGE_ERROR_LOGIN_PASSWORD, MessageManager.getProperty(MessageConstant.ERROR_LOGIN_PASSWORD));
            }

        } catch (SQLException b) {

            LOGGER.error(LoggerConstant.ERROR_LOGIN, b);

        } finally {

            connectionPool.freeConnection(connection);
        }
        return page;
    }
}