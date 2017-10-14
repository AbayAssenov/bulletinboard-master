package kz.epam.training.bulletin_board.dao.impl;

import kz.epam.training.bulletin_board.constant.SqlConstant;
import kz.epam.training.bulletin_board.dao.AbstractDao;
import kz.epam.training.bulletin_board.model.advert.PhoneAdvert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class PhoneAdvertDao extends AbstractDao<PhoneAdvert> {

    public PhoneAdvertDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object create(PhoneAdvert phoneAdvert) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.SQL_INSERT_AD_PHONE)) {

            preparedStatement.setString(1, phoneAdvert.getPhoneNumber());
            preparedStatement.setInt(2, phoneAdvert.getAdvertId());

            preparedStatement.executeUpdate();

        }
        return true;
    }

    @Override
    public List<PhoneAdvert> read(Object advertId) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<PhoneAdvert> phonesAdvert = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_SELECT_PHONE_BY_AD_ID);
            preparedStatement.setInt(1, (Integer) advertId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                phonesAdvert.add(new PhoneAdvert(resultSet.getString(1)));
            }

        } finally {

            close(resultSet);
            close(preparedStatement);
        }
        return phonesAdvert;
    }

    @Override
    public Object update(PhoneAdvert model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object delete(int id) {
        throw new UnsupportedOperationException();
    }


}
