package kz.epam.training.bulletin_board.dao;

import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.constant.SqlConstant;
import kz.epam.training.bulletin_board.model.query.SearchQuery;

/**
 * @author Abay Assenov
 */

public class UtilAdvert {

    /**
     * Sets conditions of result
     */
    private static void setCondition(StringBuilder sql, SearchQuery searchQuery) {

        if (searchQuery.getHeadingId() != null
                || searchQuery.getSearchWord() != null
                || searchQuery.getDistrictId() != null
                || searchQuery.getPriceFrom() != null
                || searchQuery.getPriceTo() != null) {

            sql.append(SqlConstant.SQL_WHERE_ONLY_CHECKED_AND_IS_NOT_ARCHIVE); //Add only checked and is not archive adverts condition

            if (searchQuery.getHeadingId() != null && searchQuery.getHeadingId() != ParameterConstant.ZERO) {

                sql.append(SqlConstant.SQL_AND_HEADING_ID).append(searchQuery.getHeadingId());// #1 This we help take it by heading id
            }

            if (searchQuery.getSearchWord() != null) {

                if (searchQuery.getInDescription()) {

                    sql.append(SqlConstant.SQL_AND_TITLE_LIKE_WITH_BRACKET).append(searchQuery.getSearchWord().trim()).append(SqlConstant.SQL_END_LIKE);// #2 This we help find it similar in title
                    sql.append(SqlConstant.SQL_OR_DESCRIPTION_LIKE).append(searchQuery.getSearchWord()).append(SqlConstant.SQL_END_LIKE_WITH_BRACKET);// #3 This we help find it similar also in description
                } else {

                    sql.append(SqlConstant.SQL_AND_TITLE_LIKE).append(searchQuery.getSearchWord().trim()).append(SqlConstant.SQL_END_LIKE);// #2 This we help find it similar in title
                }
            }

            if (searchQuery.getDistrictId() != null) {

                sql.append(SqlConstant.SQL_AND_DISTRICT_ID).append(searchQuery.getDistrictId());// #4 This we help take it by district id
            }

            if (searchQuery.getPriceFrom() != null) {

                sql.append(SqlConstant.SQL_AND_PRICE_MONEY_FROM).append(searchQuery.getPriceFrom());// #5 This we help set it start price
            }

            if (searchQuery.getPriceTo() != null) {

                sql.append(SqlConstant.SQL_AND_PRICE_MONEY_TO).append(searchQuery.getPriceTo());// #6 This we help set it finish price
            }
        }
    }

    /**
     * Sets inner join by photo for take adverts only with photo
     */
    private static void setInnerJoin(StringBuilder sql, SearchQuery searchQuery) {

        if (searchQuery.getOnlyWithPhoto()) {

            sql.append(SqlConstant.SQL_INNER_JOIN_BY_PHOTO_AD);
        }
    }

    /**
     * Sets order of result
     */
    private static void setOrder(StringBuilder sql, SearchQuery searchQuery) {

        if (searchQuery.getOrderByNew() ^ searchQuery.getOrderByCheap() ^ searchQuery.getOrderByExpensive()) {

            sql.append(SqlConstant.SQL_ORDER_BY);//Add order

            if (searchQuery.getOrderByNew()) {

                sql.append(SqlConstant.SQL_SORT_BY_NEW);//This is sort by date
            }

            if (searchQuery.getOrderByCheap()) {

                sql.append(SqlConstant.SQL_SORT_BY_CHEAP); //This sort by price first cheap
            }

            if (searchQuery.getOrderByExpensive()) {

                sql.append(SqlConstant.SQL_SORT_BY_EXPENSIVE); //This sort by price with DESC mark(revers)  first expensive
            }
        }
    }

    /**
     * Sets limit of result
     */
    private static void setLimit(StringBuilder sql, SearchQuery searchQuery) {

        if (searchQuery.getCurrentPage() != null && searchQuery.getCountAdvertOnPage() != null) {

            int orderOnPage = searchQuery.getCurrentPage()
                    * searchQuery.getCountAdvertOnPage()
                    - searchQuery.getCountAdvertOnPage();//It is need for right order of pages

            sql.append(SqlConstant.SQL_LIMIT).append(orderOnPage).append(SqlConstant.SQL_COMMA).append(searchQuery.getCountAdvertOnPage());
        }
    }

    /**
     * Creates sql query for count rows
     */
    public static String buildSqlCount(SearchQuery searchQuery) {

        StringBuilder sql = new StringBuilder();

        sql.append(SqlConstant.SQL_COUNT_AD);// This query takes count all adverts

        setInnerJoin(sql, searchQuery);
        setCondition(sql, searchQuery);

        return sql.toString();
    }

    /**
     * Creates sql query for take adverts by search parameters
     */
    public static String buildSql(SearchQuery searchQuery) {

        StringBuilder sql = new StringBuilder();

        sql.append(SqlConstant.SQL_SELECT_ALL_AD);// This query take all rows and columns only once

        setInnerJoin(sql, searchQuery);
        setCondition(sql, searchQuery);
        setOrder(sql, searchQuery);
        setLimit(sql, searchQuery);

        System.out.println("SQL=>"+sql.toString());

        return sql.toString();
    }
}
