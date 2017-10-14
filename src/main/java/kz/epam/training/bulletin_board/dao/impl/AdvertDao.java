package kz.epam.training.bulletin_board.dao.impl;

import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.constant.SqlConstant;
import kz.epam.training.bulletin_board.dao.AbstractDao;
import kz.epam.training.bulletin_board.dao.UtilAdvert;
import kz.epam.training.bulletin_board.model.advert.Advert;
import kz.epam.training.bulletin_board.model.query.SearchQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class AdvertDao extends AbstractDao<Advert> {

    public AdvertDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object update(Advert model) {
        throw new UnsupportedOperationException();
    }

    public Boolean updateArchive(int idAdvert, boolean statusIsArchive) throws SQLException {

        Boolean result;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.SQL_UPDATE_ARCHIVE_AD_BY_ID);) {

            preparedStatement.setBoolean(1, statusIsArchive);// set status item "is archive"( true/false)
            preparedStatement.setInt(2, idAdvert);// set advert id

            result = preparedStatement.executeUpdate() == ParameterConstant.ONE;
        }

        return result;
    }

    public Boolean updateCheckAdmin(int idAdvert, boolean statusCheck) throws SQLException {

        PreparedStatement preparedStatement = null;

        Boolean result;

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_UPDATE_CHECK_ADMIN_AD_BY_ID);
            preparedStatement.setBoolean(1, statusCheck);// set status item "check_admin"( true/false)
            preparedStatement.setInt(2, idAdvert);// set advert id

            result = preparedStatement.executeUpdate() == ParameterConstant.ONE;

        } finally {
            close(preparedStatement);

        }
        return result;
    }

    @Override
    public Object delete(int id) throws SQLException {

        PreparedStatement preparedStatement = null;
        Boolean result;

        try {
            preparedStatement = connection.prepareStatement(SqlConstant.SQL_DELETE_AD_BY_ID);
            preparedStatement.setInt(1, id);// set advert id

            result = preparedStatement.executeUpdate() == ParameterConstant.ONE;

        } finally {
            close(preparedStatement);
        }
        return result;
    }

    private int getCount(String sql) throws SQLException {

        int countPage = ParameterConstant.ZERO;

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ++countPage; //= resultSet.getInt(1);//Take count result
            }
        } finally {
            close(statement);
            close(resultSet);
        }
        return countPage;
    }

    public List<Advert> readAdvertsByUserId(int userId, int orderPage, int countAdvertOnPage)
            throws SQLException {

        List<Advert> advertList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_SELECT_AD_BY_USER_ID_WITH_LIMIT);
            preparedStatement.setInt(1, userId); // User id
            preparedStatement.setInt(2, orderPage); // Order page
            preparedStatement.setInt(3, countAdvertOnPage); // Count advert on page
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Advert advert = new Advert();
                advert.setId(resultSet.getInt(1));
                advert.setTitle(resultSet.getString(2));
                advert.setDate(resultSet.getDate(3));
                advert.setArchive(resultSet.getBoolean(4));

                advertList.add(advert);
            }
        } finally {

            close(resultSet);
            close(preparedStatement);
        }
        return advertList;
    }

    public int readUserId(Integer idAdvert) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Integer userId = ParameterConstant.NEGATIVE_VALUE;

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_SELECT_USER_ID_BY_AD_ID);
            preparedStatement.setInt(1, idAdvert);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                userId = resultSet.getInt(1);
            }
        } finally {

            close(preparedStatement);
            close(resultSet);
        }
        return userId;
    }


    public Advert readAdvertById(int idAdvert) throws SQLException {

        Advert advert = new Advert();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_SELECT_AD_BY_ID);
            preparedStatement.setInt(1, idAdvert);//Advert id ( chosen advert)
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                advert.setId(resultSet.getInt(1));
                advert.setTitle(resultSet.getString(2));
                advert.setHeading(resultSet.getInt(3));
                advert.setSwap(resultSet.getBoolean(4));
                advert.setDescription(resultSet.getString(5));
                advert.setDate(resultSet.getDate(6));
                advert.setDistrictId(resultSet.getInt(7));
                advert.setAddress(resultSet.getString(8));
                advert.setPriceMoney(resultSet.getString(9));
                advert.setPriceContract(resultSet.getBoolean(10));
                advert.setUserId(resultSet.getInt(11));
                advert.setCheckAdmin(resultSet.getBoolean(12));
            }

        } finally {

            close(preparedStatement);
            close(resultSet);
        }
        return advert;
    }

    public int getCountAdvertsIsUnchecked() throws SQLException {
        Statement statement = null;
        ResultSet resultSet = null;

        int countAdverts;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(SqlConstant.SQL_SELECT_COUNT_IS_UNCHECKED_AD);

            resultSet.next();
            countAdverts = resultSet.getInt(1);

        } finally {

            close(statement);
            close(resultSet);
        }

        return countAdverts;
    }


    public List<Advert> readAdvertIsUnchecked(int orderPage, int countUserOnPage) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;

        List<Advert> advertsIsUnchecked = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append(SqlConstant.SQL_READ_AD_IS_UNCHECKED_AD);
        sql.append(orderPage);
        sql.append(SqlConstant.SQL_COMMA);
        sql.append(countUserOnPage);

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql.toString());

            while (resultSet.next()) {

                Advert newAdvert = new Advert();
                newAdvert.setId(resultSet.getInt(1));
                newAdvert.setTitle(resultSet.getString(2));
                newAdvert.setDate(resultSet.getDate(3));
                newAdvert.setUserId(resultSet.getInt(4));

                advertsIsUnchecked.add(newAdvert);
            }
        } finally {

            close(statement);
            close(resultSet);
        }

        return advertsIsUnchecked;
    }


    @Override
    public List<Advert> read(Object searchQuery) throws SQLException {

        List<Advert> advertsOnPage = new ArrayList<>();//Return list of result

        String sql = UtilAdvert.buildSql((SearchQuery) searchQuery);// Get sql created query

//Start execute query
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Advert newAdvert = new Advert();
                newAdvert.setId(resultSet.getInt(1));
                newAdvert.setTitle(resultSet.getString(2));
                newAdvert.setHeading(resultSet.getInt(3));
                newAdvert.setSwap(resultSet.getBoolean(4));
                newAdvert.setDescription(resultSet.getString(5));
                newAdvert.setDate(resultSet.getDate(6));
                newAdvert.setDistrictId(resultSet.getInt(7));
                newAdvert.setAddress(resultSet.getString(8));
                newAdvert.setPriceMoney(resultSet.getString(9));
                newAdvert.setPriceContract(resultSet.getBoolean(10));
                newAdvert.setUserId(resultSet.getInt(11));
                newAdvert.setCheckAdmin(resultSet.getBoolean(12));

                advertsOnPage.add(newAdvert);
            }

        } finally {

            close(statement);
            close(resultSet);
        }

        return advertsOnPage;
    }

    public int getCountByUserId(int userId) throws SQLException {

        return getCount(SqlConstant.SQL_COUNT_AD_BY_USER + userId);
    }

    public int getCountBySearch(SearchQuery searchQuery) throws SQLException {

        final String SQL = UtilAdvert.buildSqlCount(searchQuery);

        return getCount(SQL);
    }


    @Override
    public Integer create(Advert advert) throws SQLException {

        Integer advertId = ParameterConstant.NEGATIVE_VALUE;
        ResultSet rs = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.SQL_INSERT_AD,
                Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, advert.getTitle());
            preparedStatement.setInt(2, advert.getHeading());
            preparedStatement.setBoolean(3, advert.isSwap());
            preparedStatement.setString(4, advert.getDescription());
            preparedStatement.setDate(5, advert.getDate());//Set current date
            preparedStatement.setInt(6, advert.getDistrictId());
            preparedStatement.setString(7, advert.getAddress());
            preparedStatement.setString(8, advert.getPriceMoney());
            preparedStatement.setBoolean(9, advert.isPriceContract());
            preparedStatement.setInt(10, advert.getUserId());

            preparedStatement.executeUpdate();

            rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                advertId = rs.getInt(1);//Here we take id of was written new advert
            }
        } finally {

            close(rs);
        }
        return advertId;
    }

}
