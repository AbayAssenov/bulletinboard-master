package kz.epam.training.bulletin_board.command.impl.handler;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.FormConstant;
import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.dao.AbstractDao;
import kz.epam.training.bulletin_board.dao.impl.AdvertDao;
import kz.epam.training.bulletin_board.dao.impl.PhoneAdvertDao;
import kz.epam.training.bulletin_board.dao.impl.PhotoAdvertDao;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.exception.PhotoException;
import kz.epam.training.bulletin_board.model.advert.Advert;
import kz.epam.training.bulletin_board.model.advert.PhoneAdvert;
import kz.epam.training.bulletin_board.model.advert.PhotoAdvert;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class AddAdvert implements ActionCommand {

    private final Logger LOGGER = Logger.getLogger(AddAdvert.class);

    /**
     * Create Advert from request
     */
    private Advert createAdvert(HttpServletRequest request) {

        String title = request.getParameter(ParameterConstant.TITLE);
        int heading = Integer.valueOf(request.getParameter(ParameterConstant.HEADING));
        boolean swap = request.getParameter(ParameterConstant.SWAP) != null; //this is need for check checkbox
        String description = request.getParameter(ParameterConstant.DESCRIPTION);
        int districtId = Integer.valueOf(request.getParameter(ParameterConstant.DISTRICT));
        String address = request.getParameter(ParameterConstant.ADDRESS);
        String priceMoney = request.getParameter(ParameterConstant.PRICE_MONEY);
        boolean priceContract = request.getParameter(ParameterConstant.PRICE_CONTRACT) != null; //this is need for check checkbox
        Date currentDate = new Date(System.currentTimeMillis()); //Current date for advert
        int userId = (Integer) request.getSession().getAttribute(ParameterConstant.USER_ID); //Take user id

        return new Advert(userId, title, heading, swap, description,
                currentDate, districtId, address, priceMoney, priceContract);
    }

    /**
     * Create Phone advert's from request
     */
    private List<PhoneAdvert> createPhoneAdvert(HttpServletRequest request, Integer advertId) {

        List<PhoneAdvert> phoneAdverts = new ArrayList<>();  //this is array of phones
        String tempPhoneNumber;

        for (int i = 1; i <= ParameterConstant.MAX_PHONE_COUNT; i++) {

            if (!request.getParameter(ParameterConstant.PHONE + i).isEmpty()) {//Input parameter check

                tempPhoneNumber = request.getParameter(ParameterConstant.PHONE + i);
                PhoneAdvert newPhoneNumber = new PhoneAdvert(tempPhoneNumber, advertId);
                phoneAdverts.add(newPhoneNumber);
            }
        }
        return phoneAdverts;
    }

    /**
     * Create Photo(Picture) advert's from request
     */
    private List<PhotoAdvert> createPhotoAdvert(HttpServletRequest request, Integer advertId) throws PhotoException {

        List<PhotoAdvert> photoAdverts = new ArrayList<>();    //this is array of photos

        for (int i = 1; i <= ParameterConstant.COUNT_PHOTO; i++) {
            try {
                Part filePart = request.getPart(ParameterConstant.PHOTO + i);

                if (filePart.getSize() > ParameterConstant.ZERO) {//не работает

                    InputStream inputStreamFile = filePart.getInputStream(); // input stream of the upload file
                    PhotoAdvert newPhotoAdvert = new PhotoAdvert(inputStreamFile, advertId);
                    photoAdverts.add(newPhotoAdvert);
                }
            } catch (IOException | ServletException e) {

                throw new PhotoException(LoggerConstant.ERROR_TRY_CREATE_PHOTO_ADVERT,e);
            }
        }
        return photoAdverts;
    }

    @Override
    public String execute(HttpServletRequest request) {

        ConnectionPool connectionPool = ConnectionPool.getInstance();//Переименовать
        Connection connection = connectionPool.getConnection();//Переим


        String page = ConfigurationManager.getProperty(FormConstant.FORM_MAIN);
        try {

            connection.setAutoCommit(false);

            AdvertDao advertDao = new AdvertDao(connection);
            AbstractDao phoneAdvertDao = new PhoneAdvertDao(connection);
            PhotoAdvertDao photoAdvertDao = new PhotoAdvertDao(connection);

            Advert currentAdvert = createAdvert(request);
            Integer advertId;  //ID of current advert

            advertId = advertDao.create(currentAdvert);

            List<PhotoAdvert> photoAdverts = createPhotoAdvert(request, advertId); //Take photos of advert
            List<PhoneAdvert> phoneAdverts = createPhoneAdvert(request, advertId); //Take phones of advert

            for (PhoneAdvert phoneAdvert : phoneAdverts) {
                phoneAdvertDao.create(phoneAdvert);
            }

            for (PhotoAdvert photoAdvert : photoAdverts) {
                photoAdvertDao.create(photoAdvert);
            }

            connection.commit();

            page = ConfigurationManager.getProperty(FormConstant.FORM_USER_CABINET);

        } catch (SQLException | PhotoException b) {

            try {

                connection.rollback();

            } catch (SQLException e) {

                LOGGER.error(LoggerConstant.ERROR_ADD_ADVERT, e);
            }
            LOGGER.error(LoggerConstant.ERROR_ADD_ADVERT, b);

        } finally {
            connectionPool.freeConnection(connection);
        }

        return page;
    }
}
