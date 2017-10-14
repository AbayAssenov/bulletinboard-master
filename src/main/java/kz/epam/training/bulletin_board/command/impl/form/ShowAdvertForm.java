package kz.epam.training.bulletin_board.command.impl.form;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.FormConstant;
import kz.epam.training.bulletin_board.constant.LocaleConstant;
import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.dao.impl.*;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.model.advert.Advert;
import kz.epam.training.bulletin_board.model.advert.PhoneAdvert;
import kz.epam.training.bulletin_board.model.advert.PhotoAdvert;
import kz.epam.training.bulletin_board.model.location.Region;
import kz.epam.training.bulletin_board.model.user.User;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import kz.epam.training.bulletin_board.util.CurrentLocale;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class ShowAdvertForm implements ActionCommand {

    private final Logger LOGGER = Logger.getLogger(ShowAdvertForm.class);


    /**
     * Returns all phones advert
     */
    private List<PhoneAdvert> getPhones(Integer id, Connection connection) throws SQLException {

        PhoneAdvertDao phoneAdvertDao = new PhoneAdvertDao(connection);

        return phoneAdvertDao.read(id);
    }

    /**
     * Returns all photos advert
     */
    private List<PhotoAdvert> getPhotos(Integer id, Connection connection) throws SQLException {

        PhotoAdvertDao photoAdvertDao = new PhotoAdvertDao(connection);

        return photoAdvertDao.read(id); // Get photos from data base
    }

    /**
     * Returns advert
     */
    private Advert getAdvert(Integer id, Connection connection) throws SQLException {

        AdvertDao advertDAO = new AdvertDao(connection);

        return advertDAO.readAdvertById(id);
    }

    /**
     * Returns owner user of advert
     */
    private User getUserOwner(Integer userId, Connection connection) throws SQLException {

        UserDao userDao = new UserDao(connection);

        return userDao.readUserById(userId);
    }

    private Region getLocation(int districtId, String locale, Connection connection) throws SQLException {

        LocationDao locationDao = new LocationDao(connection);

        return locationDao.readByDistrictId(districtId, locale);
    }

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getParameter(ParameterConstant.ID_ADVERT) != null) {

            ConnectionPool poolConnection = ConnectionPool.getInstance(); //Take connection
            Connection connection = poolConnection.getConnection();

            Integer id = Integer.valueOf(request.getParameter(ParameterConstant.ID_ADVERT)); //Take id of advert

            try {

                Advert advert = getAdvert(id, connection); //Get advert

                Integer userId = advert.getUserId();

                User user = getUserOwner(userId, connection);

                List<PhotoAdvert> imageAdverts = getPhotos(id, connection); //Get photos
                List<PhoneAdvert> phoneAdverts = getPhones(id, connection); //Get phones

                String locale = request.getSession().getAttribute(LocaleConstant.JSTL_FMT_LOCAL) != null ? // Take current locale
                        (String) request.getSession().getAttribute(LocaleConstant.JSTL_FMT_LOCAL)
                        : CurrentLocale.defineLocale(request.getLocale().toString());

                Region region = getLocation(advert.getDistrictId(), locale, connection); //Get location of advert

                request.setAttribute(ParameterConstant.REGION_AD, region);
                request.setAttribute(ParameterConstant.USER_OWNER_AD, user);
                request.setAttribute(ParameterConstant.ADVERT, advert); //Set found advert from data base
                request.setAttribute(ParameterConstant.ADVERT_IMAGES, imageAdverts); //Set photos
                request.setAttribute(ParameterConstant.ADVERT_PHONES, phoneAdverts); //Set phones

                if (advert.isPriceContract())
                    request.setAttribute(ParameterConstant.PRICE_CONTRACT, advert.isPriceContract()); // Mark contract price

                if (advert.isSwap()) request.setAttribute(ParameterConstant.SWAP, advert.isSwap()); // Mark have swap

            } catch (SQLException b) {

                LOGGER.error(LoggerConstant.ERROR_SHOW_ADVERT, b);
            } finally {
                poolConnection.freeConnection(connection);
            }
        }

        return ConfigurationManager.getProperty(FormConstant.FORM_ADVERT);
    }
}
