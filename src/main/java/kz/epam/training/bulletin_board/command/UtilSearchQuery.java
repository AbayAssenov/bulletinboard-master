package kz.epam.training.bulletin_board.command;

import kz.epam.training.bulletin_board.constant.PageConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.constant.SqlConstant;
import kz.epam.training.bulletin_board.model.query.SearchQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Abay Assenov
 */

public class UtilSearchQuery {

    /** Create object "search query" for getting result from data base*/

    public static SearchQuery createSearchQuery(HttpServletRequest request) {

        Integer currentPage = request.getParameter(PageConstant.SELECTED_PAGE) == null
                ? PageConstant.DEFAULT_SELECTED_PAGE
                : Integer.valueOf(request.getParameter(PageConstant.SELECTED_PAGE));  //1

        Integer countAdvertOnPage = PageConstant.ADVERT_ON_ONE_PAGE; //2

        Integer headingId = null; //3
        String searchWord = null; //4
        Boolean inDescription = false; //5
        Integer districtId = null; //6
        Integer priceFrom = null; //7
        Integer priceTo = null; //8
        Boolean orderByNew = false; //9
        Boolean orderByCheap = false; //10
        Boolean orderByExpensive = false; //11
        Boolean onlyWithPhoto = false; //12

        if (request.getParameter(ParameterConstant.ID_HEADING) != null) {
            headingId = new Integer(request.getParameter(ParameterConstant.ID_HEADING)); // #3
        }
        if (request.getParameter(ParameterConstant.SEARCH_WORD) != null) {
            searchWord = request.getParameter(ParameterConstant.SEARCH_WORD); // #4
        }
        if (request.getParameter(ParameterConstant.IN_DESCRIPTION) != null) {
            inDescription = Boolean.valueOf(request.getParameter(ParameterConstant.IN_DESCRIPTION)); //#5
        }
        if (request.getParameter(ParameterConstant.ID_DISTRICT) != null) {
            districtId = new Integer(request.getParameter(ParameterConstant.ID_DISTRICT)); //#6
        }
        if (request.getParameter(ParameterConstant.PRICE_FROM) != null) {
            priceFrom = new Integer(request.getParameter(ParameterConstant.PRICE_FROM)); //#7
        }
        if (request.getParameter(ParameterConstant.PRICE_TO) != null) {
            priceTo = new Integer(request.getParameter(ParameterConstant.PRICE_TO)); //#8
        }
        if (request.getParameter(SqlConstant.ORDER_BY_NEW) != null) {
            orderByNew = Boolean.valueOf(request.getParameter(SqlConstant.ORDER_BY_NEW)); //#9
        }
        if (request.getParameter(SqlConstant.ORDER_BY_CHEAP) != null) {
            orderByCheap = Boolean.valueOf(request.getParameter(SqlConstant.ORDER_BY_CHEAP)); //#10
        }
        if (request.getParameter(SqlConstant.ORDER_BY_EXPENSIVE) != null) {
            orderByExpensive = Boolean.valueOf(request.getParameter(SqlConstant.ORDER_BY_EXPENSIVE)); //11
        }
        if (request.getParameter(ParameterConstant.ONLY_WITH_PHOTO) != null) {
            onlyWithPhoto = Boolean.valueOf(request.getParameter(ParameterConstant.ONLY_WITH_PHOTO)); //12
        }

        SearchQuery searchQuery = new SearchQuery();

        searchQuery.setCountAdvertOnPage(countAdvertOnPage);
        searchQuery.setCurrentPage(currentPage);
        searchQuery.setDistrictId(districtId);
        searchQuery.setHeadingId(headingId);
        searchQuery.setInDescription(inDescription);
        searchQuery.setSearchWord(searchWord);
        searchQuery.setPriceFrom(priceFrom);
        searchQuery.setPriceTo(priceTo);
        searchQuery.setOrderByCheap(orderByCheap);
        searchQuery.setOrderByNew(orderByNew);
        searchQuery.setOrderByExpensive(orderByExpensive);
        searchQuery.setOnlyWithPhoto(onlyWithPhoto);

        return searchQuery;
    }
}
