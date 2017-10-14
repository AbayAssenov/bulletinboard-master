package kz.epam.training.bulletin_board.command;

import kz.epam.training.bulletin_board.constant.LocaleConstant;
import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.dao.impl.HeadingDao;
import kz.epam.training.bulletin_board.dao.impl.LocationDao;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import kz.epam.training.bulletin_board.model.heading.Heading;
import kz.epam.training.bulletin_board.model.location.Region;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Abay Assenov
 */
public class CommonData {

    private final Logger LOGGER = Logger.getLogger(CommonData.class);

    private void setLocation(String locale,ServletConfig config, Connection connection) throws SQLException {

        LocationDao locationDao = new LocationDao(connection);

        List<Region> location = locationDao.readAll(locale); //Take all regions

        config.getServletContext().setAttribute(ParameterConstant.LOCATION+locale, location); //Set in common cache location by language
    }

    private void setHeadings(String locale,ServletConfig config, Connection connection) throws SQLException {

        HeadingDao headingDAO = new HeadingDao(connection);// set connection to dao

        List<Heading> headings = headingDAO.readAll(locale);//Take all headings

        config.getServletContext().setAttribute(ParameterConstant.HEADINGS+locale, headings); //Set in common cache headings by language
    }

    public void execute(ServletConfig config) {

        ConnectionPool pool = ConnectionPool.getInstance();//Take connection db
        Connection connection = pool.getConnection();// create variable type connection

        try {

            setLocation(LocaleConstant.LOCALE_ENG,config, connection); //Set location
            setLocation(LocaleConstant.LOCALE_RUS,config, connection);

            setHeadings(LocaleConstant.LOCALE_ENG,config, connection); //Set headings
            setHeadings(LocaleConstant.LOCALE_RUS,config, connection);

        } catch (SQLException b) {

            LOGGER.error(LoggerConstant.ERROR_COMMON_DATA, b);

        } finally {

            pool.freeConnection(connection);//to free connection
        }
    }
}


