package kz.epam.training.bulletin_board.command.impl.form;

import kz.epam.training.bulletin_board.bean.PaginationBean;
import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.*;
import kz.epam.training.bulletin_board.dao.impl.AdvertDao;
import kz.epam.training.bulletin_board.dao.impl.PhotoAdvertDao;
import kz.epam.training.bulletin_board.dao.impl.UserDao;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.model.BoardImage;
import kz.epam.training.bulletin_board.model.advert.Advert;
import kz.epam.training.bulletin_board.model.user.User;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Abay Assenov
 */

public class UserCabinetForm implements ActionCommand {

    private final Logger LOGGER = Logger.getLogger(UserCabinetForm.class);

    /**
     * Sets selected block
     */
    private void setSelectedBlock(HttpServletRequest request) {

        Integer block = request.getParameter(CabinetConstant.BLOCK) == null
                ? ParameterConstant.ONE // This is display first block on user cabinet page
                : Integer.valueOf(request.getParameter(CabinetConstant.BLOCK));  //1

        request.setAttribute(CabinetConstant.BLOCK, block); //HERE
    }

    /**
     * Sets user's adverts on page
     */
    private void setUserAdverts(HttpServletRequest request, Connection connection) throws SQLException {

        AdvertDao advertDao = new AdvertDao(connection);

        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID);

        int currentPage = request.getParameter(PageConstant.SELECTED_PAGE) == null  //Take current page
                ? PageConstant.DEFAULT_SELECTED_PAGE
                : Integer.valueOf(request.getParameter(PageConstant.SELECTED_PAGE));

        int countAdverts = PageConstant.DEFAULT_DISPLAY_USER_AD; // default display only 5 adverts
        int orderPage = currentPage * countAdverts - countAdverts; //This settings for display in right order on the page
        int pagination = PaginationBean.getPagination(countAdverts, advertDao.getCountByUserId(userId)); //Get settings for pagination

        List<Advert> userAdverts = advertDao.readAdvertsByUserId(userId, orderPage, countAdverts); //Take user's adverts

        setPhotoAdvert(userAdverts, request, connection); //Set advert photos

        request.setAttribute(ParameterConstant.LIST_ADVERTS, userAdverts); //Set result of adverts by user id
        request.setAttribute(PageConstant.COUNT_PAGE, pagination); //Settings for pagination
    }

    /**
     * Takes  photos of adverts
     */
    private void setPhotoAdvert(List<Advert> adverts, HttpServletRequest request, Connection connection) throws SQLException {

        PhotoAdvertDao photoAdvertDao = new PhotoAdvertDao(connection);

        Map<Integer, BoardImage> firstImagesAdverts = new HashMap<>();

        for (Advert currentAdvert : adverts) { //Walks one by one all id adverts

            int advertId = currentAdvert.getId();//Gets advert id

            if (photoAdvertDao.readFirstPhotoByAdvertId(advertId) != null) {

                BoardImage advertImage = photoAdvertDao.readFirstPhotoByAdvertId(advertId); //Takes first photos from data base

                firstImagesAdverts.put(advertId, advertImage);
            }
        }
        request.setAttribute(ParameterConstant.FIRST_ADVERTS_IMAGES, firstImagesAdverts); //Settings for pagination
    }

    /**
     * Sets user's profile on page
     */
    private void setUserProfile(HttpServletRequest request, Connection connection) throws SQLException {

        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID);

        UserDao userDao = new UserDao(connection);
        User user = userDao.readUserById(userId);

        request.setAttribute(ParameterConstant.USER_NAME, user.getUserName()); //Set user name
        request.setAttribute(ParameterConstant.USER_ID, user.getUserId()); //Set user id
        request.setAttribute(ParameterConstant.USER_EMAIL, user.getUserEmail()); //Set user email
        request.setAttribute(ParameterConstant.USER_PHOTO, user.getPhotoUser()); //Set user  photo
    }

    @Override
    public String execute(HttpServletRequest request) {

        if (RoleConstant.USER.equals(request.getSession().getAttribute(RoleConstant.ROLE))) {

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            setSelectedBlock(request); //Set selected item menu

            try {

                if (request.getParameter(CabinetConstant.USER_CABINET_COMMAND) != null) {

                    String userCabinetAction = request.getParameter(CabinetConstant.USER_CABINET_COMMAND);

                    switch (userCabinetAction) { //Define user's action

                        case CabinetConstant.CABINET_COMMAND_PROFILE:
                            setUserProfile(request, connection);
                            break;
                        case CabinetConstant.CABINET_COMMAND_USER_ADVERTS:
                            setUserAdverts(request, connection);
                            break;
                    }
                }

            } catch (SQLException b) {

                LOGGER.error(LoggerConstant.ERROR_USER_CABINET, b);

            } finally {
                pool.freeConnection(connection);

            }
        }

        return ConfigurationManager.getProperty(FormConstant.FORM_USER_CABINET);
    }
}


