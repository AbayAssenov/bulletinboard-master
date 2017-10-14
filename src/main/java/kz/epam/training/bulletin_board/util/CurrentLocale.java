package kz.epam.training.bulletin_board.util;

import kz.epam.training.bulletin_board.constant.LocaleConstant;

/**
 * @author Abay Assenov
 *         9/22/2017
 */
public class CurrentLocale {

    public static String  defineLocale(String locale) {

            switch (locale) {
                case LocaleConstant.LOCALE_ENG: return LocaleConstant.LOCALE_ENG;
                case LocaleConstant.LOCALE_RUS: return LocaleConstant.LOCALE_RUS;
                default: return LocaleConstant.LOCALE_ENG;
            }
        }
    }

