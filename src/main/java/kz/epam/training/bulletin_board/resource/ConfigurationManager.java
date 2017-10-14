package kz.epam.training.bulletin_board.resource;

import kz.epam.training.bulletin_board.constant.ParameterConstant;

import java.util.ResourceBundle;

/**
 * @author Abay Assenov
 */

public class ConfigurationManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(ParameterConstant.CONFIG);

    // this class pull out information of file config.properties

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {

        return resourceBundle.getString(key);
    }
}