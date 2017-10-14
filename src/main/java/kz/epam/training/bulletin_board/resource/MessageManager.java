package kz.epam.training.bulletin_board.resource;

import kz.epam.training.bulletin_board.constant.ParameterConstant;

import java.util.ResourceBundle;

/**
 * @author Abay Assenov
 */

public class MessageManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(ParameterConstant.MESSAGES);

    // this class pull out information of file messages.properties
    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}