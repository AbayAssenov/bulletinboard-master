package kz.epam.training.bulletin_board.util;

import kz.epam.training.bulletin_board.constant.ParameterConstant;

import java.util.Base64;

/**
 * @author Abay Assenov
 */

public class Converter {

    /**
     * Convert byte[] to String use base64 encoding
     */

    public String convertToString(byte[] data) {

        if (data != null) return Base64.getEncoder().encodeToString(data);
        else return ParameterConstant.EMPTY_STRING;
    }


}
