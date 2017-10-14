package kz.epam.training.bulletin_board.bean;

import kz.epam.training.bulletin_board.constant.ParameterConstant;

/**
 * @author Abay Assenov
 */

public class PaginationBean {
    public static int getPagination(int countOnOnePage, int maxCountEntity) {

        int countPage;

        if (maxCountEntity % countOnOnePage == ParameterConstant.ZERO) { //If divide without remainder
            //if it is not equals zero then just it max count entity divide on need count on page
            countPage = countOnOnePage > ParameterConstant.ZERO ? (maxCountEntity / countOnOnePage) : ParameterConstant.ZERO;

        } else {
            //if it is not equals zero then just it max count entity divide on need count on page plus one
            countPage = countOnOnePage > ParameterConstant.ZERO ? (maxCountEntity / countOnOnePage) + ParameterConstant.ONE : ParameterConstant.ZERO;
        }
        return countPage;
    }
}
