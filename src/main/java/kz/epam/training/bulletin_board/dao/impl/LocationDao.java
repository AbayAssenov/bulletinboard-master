package kz.epam.training.bulletin_board.dao.impl;

import kz.epam.training.bulletin_board.constant.SqlConstant;
import kz.epam.training.bulletin_board.dao.AbstractDao;
import kz.epam.training.bulletin_board.model.location.District;
import kz.epam.training.bulletin_board.model.location.Region;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abay Assenov
 */

public class LocationDao extends AbstractDao<Region> {

    public LocationDao(Connection connection) {
        super(connection);
    }

    @Override
    public Object create(Region model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Region> read(Object parameter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object update(Region model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object delete(int id) {
        throw new UnsupportedOperationException();
    }

    public Region readByDistrictId(int idDistrict, String locale) throws SQLException {

        PreparedStatement preparedStatement = null;
        PreparedStatement innerPreparedStatement = null;
        ResultSet resultSet = null;
        ResultSet innerResultSet = null;

        StringBuilder sqlRegion = new StringBuilder();
        sqlRegion.append(SqlConstant.SQL_SELECT_LOCATION_BY_ID_1).append(locale).append(SqlConstant.SQL_SELECT_LOCATION_BY_ID_2);


        Region region = new Region();

        try {
            preparedStatement = connection.prepareStatement(sqlRegion.toString());
            innerPreparedStatement = connection.prepareStatement(sqlRegion.toString());

            preparedStatement.setInt(1, idDistrict); //Set id district
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                District district = new District();
                district.setDistrictName(resultSet.getString(1)); //Take district name

                int idParent = resultSet.getInt(2); //Take id parent (id of region)

                innerPreparedStatement.setInt(1, idParent); //Set id parent (region)
                innerResultSet = innerPreparedStatement.executeQuery();

                if (innerResultSet.next()) {

                    region.setRegionName(innerResultSet.getString(1)); //Take region name
                    region.addDistrict(district); //Add district in region
                }
            }
        } finally {

            close(innerResultSet);
            close(resultSet);
            close(innerPreparedStatement);
            close(preparedStatement);
        }

        return region;
    }

    public List<Region> readAll(String locale) throws SQLException {

        List<Region> regions = new ArrayList<>();

        Statement st = null;
        ResultSet rs = null;
        Statement stInner = null;
        ResultSet rsInner = null;

        StringBuilder sqlRegions = new StringBuilder();
        StringBuilder sqlDistricts = new StringBuilder();
        sqlRegions.append(SqlConstant.SQL_SELECT_ALL_REGIONS_1).append(locale).append(SqlConstant.SQL_SELECT_ALL_REGIONS_2);
        sqlDistricts.append(SqlConstant.SQL_SELECT_ALL_DISTRICT_BY_REGION_ID_1).append(locale).append(SqlConstant.SQL_SELECT_ALL_DISTRICT_BY_REGION_ID_2);

        try {

            st = connection.createStatement();
            stInner = connection.createStatement();
            rs = st.executeQuery(sqlRegions.toString());

            while (rs.next()) {

                Region currentRegion = new Region();
                currentRegion.setRegionId(rs.getInt(1));
                currentRegion.setRegionName(rs.getString(2));
                rsInner = stInner.executeQuery(sqlDistricts.toString() + rs.getInt(1));
                while (rsInner.next()) {

                    District currentDistrict = new District();
                    currentDistrict.setDistrictId(rsInner.getInt(1));
                    currentDistrict.setDistrictName(rsInner.getString(2));
                    currentRegion.addDistrict(currentDistrict);
                }
                regions.add(currentRegion);
            }

        } finally {

            close(rsInner);
            close(rs);
            close(stInner);
            close(st);
        }

        return regions;
    }
}
