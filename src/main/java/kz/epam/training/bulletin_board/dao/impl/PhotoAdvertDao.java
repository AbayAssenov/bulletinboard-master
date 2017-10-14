package kz.epam.training.bulletin_board.dao.impl;

import kz.epam.training.bulletin_board.constant.SqlConstant;
import kz.epam.training.bulletin_board.dao.AbstractDao;
import kz.epam.training.bulletin_board.model.BoardImage;
import kz.epam.training.bulletin_board.model.advert.PhotoAdvert;
import kz.epam.training.bulletin_board.util.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class PhotoAdvertDao extends AbstractDao<PhotoAdvert> {

    public PhotoAdvertDao(Connection connection) {
        super(connection);
    }


    @Override
    public Object create(PhotoAdvert photoAdvert) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.SQL_INSERT_PHOTO_AD)) {

            preparedStatement.setBlob(1, photoAdvert.getPhotoAdvertStream());
            preparedStatement.setInt(2, photoAdvert.getAdvertId());

            preparedStatement.executeUpdate();

        }
        return true;
    }

    @Override
    public List<PhotoAdvert> read(Object advertId) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<PhotoAdvert> imageList = new ArrayList<>();

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_PHOTO_AD_BY_AD_ID);
            preparedStatement.setInt(1, (Integer) advertId);
            resultSet = preparedStatement.executeQuery();

            Converter converter = new Converter(); //base64 encoding byte[] to String

            while (resultSet.next()) {

                PhotoAdvert image = new PhotoAdvert();
                image.setImage(converter.convertToString(resultSet.getBytes(2)));

                imageList.add(image);
            }

        } finally {

            close(preparedStatement);
            close(resultSet);
        }
        return imageList;
    }

    @Override
    public Object update(PhotoAdvert model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object delete(int id) {
        throw new UnsupportedOperationException();
    }

    public BoardImage readFirstPhotoByAdvertId(int advertId) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Converter converter = new Converter();
        BoardImage boardImage = new BoardImage();

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_FIRST_PHOTO_AD_BY_AD_ID);
            preparedStatement.setInt(1, advertId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                boardImage.setImage(converter.convertToString(resultSet.getBytes(1)));

            }
        } finally {

            close(preparedStatement);
            close(resultSet);
        }

        return boardImage;
    }

}
