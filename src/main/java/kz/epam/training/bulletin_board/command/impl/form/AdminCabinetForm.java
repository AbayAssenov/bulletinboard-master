package kz.epam.training.bulletin_board.command.impl.form;

import kz.epam.training.bulletin_board.bean.PaginationBean;
import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.*;
import kz.epam.training.bulletin_board.dao.impl.AdvertDao;
import kz.epam.training.bulletin_board.dao.impl.UserDao;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.model.advert.Advert;
import kz.epam.training.bulletin_board.model.user.User;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class AdminCabinetForm implements ActionCommand {


    private final Logger LOGGER = Logger.getLogger(AdminCabinetForm.class);


    /**
     * Returns count adverts of each user
     */
    private List<Integer> getCountAdvertEachUser(List<User> userList, AdvertDao advertDao) throws SQLException {

        List<Integer> countAdvertEachUser = new ArrayList<>();

        for (User user : userList) {

            countAdvertEachUser.add(advertDao.getCountByUserId(user.getUserId()));
        }
        return countAdvertEachUser;
    }

    /**
     * Sets selected block
     */
    private void setSelectedBlock(HttpServletRequest request) {

        Integer block = request.getParameter(CabinetConstant.BLOCK) == null
                ? ParameterConstant.ONE // This is display first block on admin cabinet page
                : Integer.valueOf(request.getParameter(CabinetConstant.BLOCK));  //1

        request.setAttribute(CabinetConstant.BLOCK, block); //HERE
    }


    private void setUsersOnPage(HttpServletRequest request, Connection connection) throws SQLException {

        AdvertDao advertDao = new AdvertDao(connection);
        UserDao userDao = new UserDao(connection);

        Integer currentPage = request.getParameter(PageConstant.SELECTED_PAGE_USERS) == null
                ? PageConstant.DEFAULT_SELECTED_PAGE
                : Integer.valueOf(request.getParameter(PageConstant.SELECTED_PAGE_USERS));  //1

        int paginationUsers = PaginationBean.getPagination(PageConstant.USERS_ON_ONE_PAGE, userDao.getCount()); //Get pagination//
        int countUsers = PageConstant.USERS_ON_ONE_PAGE;
        int orderOnPage = currentPage * countUsers - countUsers;//It is need for right order of pages //HERE

        List<User> userList = userDao.findAllByPage(orderOnPage, countUsers); //HERE

        List<Integer> countAdvertEachUser = getCountAdvertEachUser(userList, advertDao); //Take count adverts users' //HERE

        request.setAttribute(ParameterConstant.LIST_USERS, userList); // Set list users
        request.setAttribute(ParameterConstant.COUNT_ADVERT, countAdvertEachUser); //Set count adverts  users'
        request.setAttribute(PageConstant.COUNT_PAGE_USERS, paginationUsers); //Settings for pagination users
    }

    private void setAdvertsIsUnchecked(HttpServletRequest request, Connection connection) throws SQLException {

        AdvertDao advertDao = new AdvertDao(connection);

        int currentPage = request.getParameter(PageConstant.SELECTED_PAGE_UNCHECKED_AD) == null  //Take current page
                ? PageConstant.DEFAULT_SELECTED_PAGE
                : Integer.valueOf(request.getParameter(PageConstant.SELECTED_PAGE_UNCHECKED_AD));

        int countAdverts = PageConstant.DEFAULT_DISPLAY_UNCHECK_AD; // default display only 5 adverts
        int orderPage = currentPage * countAdverts - countAdverts; //This settings for display in right order on the page
        int pagination = PaginationBean.getPagination(countAdverts, advertDao.getCountAdvertsIsUnchecked()); //Get settings for pagination

        List<Advert> advertsIsUnchecked = advertDao.readAdvertIsUnchecked(orderPage, countAdverts); //Take unchecked adverts

        request.setAttribute(PageConstant.ADVERTS_IS_UNCHECKED, advertsIsUnchecked);
        request.setAttribute(PageConstant.COUNT_PAGE_AD_IS_UNCHECKED, pagination);
    }

    /**
     * Sets admin's profile
     */
    private void setAdminProfile(HttpServletRequest request, Connection connection) throws SQLException  {

        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID);

        UserDao userDao = new UserDao(connection);
        User user = userDao.readUserById(userId);

        request.setAttribute(ParameterConstant.USER_NAME, user.getUserName()); //Set user name
        request.setAttribute(ParameterConstant.USER_EMAIL, user.getUserEmail()); //Set user email
        request.setAttribute(ParameterConstant.USER_PHOTO, user.getPhotoUser()); //Set user  photo
    }

    @Override
    public String execute(HttpServletRequest request) {

        if (RoleConstant.ADMIN.equals(request.getSession().getAttribute(RoleConstant.ROLE))) {  //Checking by id and role_user

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            setSelectedBlock(request);   //Set selected block (item menu)

            try {

                if (request.getParameter(CabinetConstant.ADMIN_CABINET_COMMAND) != null) {

                    String adminCabinetAction = request.getParameter(CabinetConstant.ADMIN_CABINET_COMMAND);

                    switch (adminCabinetAction) { //Define admin's action

                        case CabinetConstant.CABINET_COMMAND_PROFILE:
                            setAdminProfile(request, connection); //Set user profile
                            break;
                        case CabinetConstant.CABINET_COMMAND_ADVERTS_IS_UNCHECKED:
                            setAdvertsIsUnchecked(request, connection); //Set adverts
                            break;
                        case CabinetConstant.CABINET_COMMAND_USERS_ON_ONE_PAGE:
                            setUsersOnPage(request, connection); //Set users list
                            break;
                    }
                }
            } catch (SQLException b) {

                LOGGER.error(LoggerConstant.ERROR_ADMIN_CABINET, b);
            } finally {
                pool.freeConnection(connection); // Return connection
            }
        }

        return ConfigurationManager.getProperty(FormConstant.FORM_ADMIN_CABINET);
    }
}
