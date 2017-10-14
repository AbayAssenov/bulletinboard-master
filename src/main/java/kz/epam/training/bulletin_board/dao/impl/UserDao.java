package kz.epam.training.bulletin_board.dao.impl;

import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.constant.SqlConstant;
import kz.epam.training.bulletin_board.dao.AbstractDao;
import kz.epam.training.bulletin_board.model.user.PhotoUser;
import kz.epam.training.bulletin_board.model.user.User;
import kz.epam.training.bulletin_board.util.Converter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class UserDao extends AbstractDao<User> {

    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object create(User user) throws SQLException {

        // Take off to properties file
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.SQL_INSERT_USER)) {

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserEmail());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();

        }
        return true;
    }

    public boolean findEmail(User user) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Boolean result;

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_SELECT_USER_EMAIL);
            preparedStatement.setString(1, user.getUserEmail());// set user email
            resultSet = preparedStatement.executeQuery();

            result = resultSet.next();

        } finally {

            close(resultSet);
            close(preparedStatement);
        }

        return result;
    }

    public boolean find(User user) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean isUser = false;//Default value "this is not our user"

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_SELECT_USER_BY_EMAIL_PASSWORD);

            preparedStatement.setString(1, user.getUserEmail());//user email
            preparedStatement.setString(2, user.getPassword());//user password

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                user.setUserId(resultSet.getInt(1));//Take user id of  database
                user.setUserName(resultSet.getString(2));//Take user name of database
                user.setUserRole(resultSet.getInt(3));//Take user role of database

                isUser = true;// Set value "this is our user"
            }

        } finally {

            close(resultSet);
            close(preparedStatement);
        }
        return isUser;
    }

    @Override
    public List<User> read(Object parameter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object update(User model) {
        throw new UnsupportedOperationException();
    }


    public void updatePhoto(PhotoUser photoUser) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.SQL_UPDATE_USER_PHOTO)) {

            preparedStatement.setBlob(1, photoUser.getPhotoAdvertStream());
            preparedStatement.setInt(2, photoUser.getUserId());

            preparedStatement.executeUpdate();

        }
    }

    public Boolean updatePassword(Integer usserId, String newPassword) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstant.SQL_UPDATE_USER_PASSWORD)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, usserId);

            preparedStatement.executeUpdate();

        }
        return true;

    }

    @Override
    public Object delete(int id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public String readUserPassword(int id) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String password = ParameterConstant.EMPTY_STRING;

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_SELECT_USER_PASSWORD);
            preparedStatement.setInt(1, id);// set user id
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                password = resultSet.getString(1);
            }
        } finally {

            close(resultSet);
            close(preparedStatement);
        }
        return password;

    }

    public User readUserById(int id) throws SQLException {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Converter converter = new Converter();

        User user = new User();

        try {

            preparedStatement = connection.prepareStatement(SqlConstant.SQL_SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);//user email
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                user.setUserId(resultSet.getInt(1)); //Take user id
                user.setUserName(resultSet.getString(2)); //Take user name
                user.setUserEmail(resultSet.getString(3)); //Take user role
                user.setPhotoUser(converter.convertToString(resultSet.getBytes(4))); //Take photo
            }
        } finally {

            close(resultSet);
            close(preparedStatement);
        }
        return user;
    }


    public int getCount() throws SQLException {

        int countPage = ParameterConstant.ZERO;

        Statement statement = null;
        ResultSet resultSet = null;
        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(SqlConstant.SQL_COUNT_USERS);

            while (resultSet.next()) {
                countPage = resultSet.getInt(1);//Take count result
            }
        } finally {

            close(resultSet);
            close(statement);
        }
        return countPage;
    }

    public List<User> findAllByPage(int orderPage, int countUserOnPage) throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;

        List<User> userList = new ArrayList<>();

        Converter converter = new Converter();

        StringBuilder sql = new StringBuilder();
        sql.append(SqlConstant.SQL_SELECT_USERS_WITH_LIMIT);
        sql.append(orderPage);
        sql.append(SqlConstant.SQL_COMMA);
        sql.append(countUserOnPage);

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql.toString());

            while (resultSet.next()) {

                User user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setUserName(resultSet.getString(2));
                user.setUserEmail(resultSet.getString(3));
                user.setPhotoUser(converter.convertToString(resultSet.getBytes(4)));

                userList.add(user);
            }

        } finally {

            close(resultSet);
            close(statement);
        }

        return userList;
    }
}
