package kz.epam.training.bulletin_board.dao;

import kz.epam.training.bulletin_board.constant.LoggerConstant;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Abay Assenov
 */

public abstract class AbstractDao<T> {

    protected Connection connection;

    private final Logger LOGGER = Logger.getLogger(AbstractDao.class);


    public AbstractDao(Connection connection) {

        this.connection = connection;
    }

    public abstract Object create(T model) throws SQLException; //CREATE

    public abstract List<T> read(Object parameter) throws SQLException; //READ

    public abstract Object update(T model) throws SQLException; //UPDATE

    public abstract Object delete(int id) throws SQLException; //DELETE

    public void close(ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.error(LoggerConstant.ERROR_ABSTRACT_DAO_CLOSE_RESUL_SET, e);
            }
        }
    }

    public void close(Statement st) {//Statement common interface

        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                LOGGER.error(LoggerConstant.ERROR_ABSTRACT_DAO_CLOSE_STATEMENT,e);
            }
        }
    }
}