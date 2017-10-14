package kz.epam.training.bulletin_board.command.impl.handler;

import kz.epam.training.bulletin_board.bean.PaginationBean;
import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.command.UtilSearchQuery;
import kz.epam.training.bulletin_board.constant.FormConstant;
import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.constant.PageConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.dao.impl.AdvertDao;
import kz.epam.training.bulletin_board.dao.impl.PhotoAdvertDao;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.model.BoardImage;
import kz.epam.training.bulletin_board.model.advert.Advert;
import kz.epam.training.bulletin_board.model.query.SearchQuery;
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

public class Search implements ActionCommand {

    private final Logger LOGGER = Logger.getLogger(Search.class);

    /**
     * Returns count of page for pagination
     */
    private Integer getPageCount(SearchQuery searchQuery, Connection connection) throws SQLException {

        AdvertDao advertDao = new AdvertDao(connection);

        Integer countAdvertOnPage = searchQuery.getCountAdvertOnPage();

        return PaginationBean.getPagination(countAdvertOnPage, advertDao.getCountBySearch(searchQuery));
    }

    /**Gets all adverts that relevant search query */
    private List<Advert> getAdverts(SearchQuery searchQuery,Connection connection) throws SQLException{

        AdvertDao advertDao = new AdvertDao(connection);

        return advertDao.read(searchQuery); //Take adverts by search query
    }

    /**
     * Gets first photos of adverts
     */
    private Map getPhotoAdvert(List<Advert> adverts, Connection connection)  throws SQLException{

        PhotoAdvertDao photoAdvertDao = new PhotoAdvertDao(connection);

        Map<Integer, BoardImage> firstImagesAdverts = new HashMap<>();

        for (Advert currentAdvert : adverts) { //Walks one by one all id adverts

            int advertId = currentAdvert.getId();//Gets advert id

                BoardImage image=photoAdvertDao.readFirstPhotoByAdvertId(advertId); //Takes first photos from data base

                firstImagesAdverts.put(advertId,image);

        }
        return firstImagesAdverts;
    }

    @Override
    public String execute(HttpServletRequest request) {

        if (request.getParameter(ParameterConstant.ID_HEADING) != null) {

            ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();

            try {

                SearchQuery searchQuery = UtilSearchQuery.createSearchQuery(request); //Create search query from request parameters

                List<Advert> adverts = getAdverts(searchQuery, connection); //Take adverts

                Map firstImagesAdverts = getPhotoAdvert(adverts, connection); //Take photos

                int pagination = getPageCount(searchQuery, connection); //Get pagination for current adverts

                request.setAttribute(ParameterConstant.ADVERT_IMAGES, firstImagesAdverts);  //Set photos of adverts
                request.setAttribute(ParameterConstant.LIST_ADVERTS, adverts);  //Set result of adverts by search query
                request.setAttribute(ParameterConstant.ID_HEADING, searchQuery.getHeadingId());  //Set selected id heading
                request.setAttribute(PageConstant.COUNT_PAGE, pagination);  //Settings for pagination

            }catch (SQLException b){

                LOGGER.error(LoggerConstant.ERROR_SEARCH, b);

            }finally {

                pool.freeConnection(connection);
            }
        }

        return ConfigurationManager.getProperty(FormConstant.FORM_BODY_SEARCH_RESULT);
    }
}

