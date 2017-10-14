package kz.epam.training.bulletin_board.dao.impl;

import kz.epam.training.bulletin_board.constant.SqlConstant;
import kz.epam.training.bulletin_board.dao.AbstractDao;
import kz.epam.training.bulletin_board.model.heading.Heading;
import kz.epam.training.bulletin_board.util.Converter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class HeadingDao extends AbstractDao<Heading> {


    public HeadingDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object create(Heading model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Heading> read(Object parameter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object update(Heading model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object delete(int id) {
        throw new UnsupportedOperationException();
    }

    public List<Heading> readAll(String locale) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;

        List<Heading> headings = new ArrayList<>();

        Converter converterPhoto = new Converter(); //Convert byte[] to String use base64 encoding

        StringBuilder sql = new StringBuilder();

        sql.append(SqlConstant.SQL_SELECT_ALL_HEADINGS_BY_LOCALE_1)
                .append(locale).append(SqlConstant.SQL_SELECT_ALL_HEADINGS_BY_LOCALE_2);

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql.toString());

            while (resultSet.next()) {

                String image = converterPhoto.convertToString(resultSet.getBytes(3));

                Heading newHeading = new Heading(resultSet.getInt(1),
                        resultSet.getString(2), image);

                headings.add(newHeading);
            }

        } finally {

            close(resultSet);
            close(statement);
        }

        return headings;
    }

}
