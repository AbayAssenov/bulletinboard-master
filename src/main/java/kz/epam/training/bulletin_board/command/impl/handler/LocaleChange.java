package kz.epam.training.bulletin_board.command.impl.handler;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.LocaleConstant;
import kz.epam.training.bulletin_board.util.CurrentLocale;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Abay Assenov
 */
public class LocaleChange implements ActionCommand {

    /**
     * Sets locale
     */
    private void setLocale(HttpServletRequest request) {

        if (request.getParameter(LocaleConstant.LOCALE) != null) {

            String locale = request.getParameter(LocaleConstant.LOCALE);

            switch (CurrentLocale.defineLocale(locale)) {

                case LocaleConstant.LOCALE_ENG:
                    request.getSession().setAttribute(LocaleConstant.JSTL_FMT_LOCAL, LocaleConstant.LOCALE_ENG);
                    break;
                case LocaleConstant.LOCALE_RUS:
                    request.getSession().setAttribute(LocaleConstant.JSTL_FMT_LOCAL, LocaleConstant.LOCALE_RUS);
                    break;
            }
        }
    }

    /**
     * Returns url of request header
     */
    private String currentUrl(HttpServletRequest request) {

        return request.getHeader(LocaleConstant.REQUEST_HEADER_REFERER);
    }

    @Override
    public String execute(HttpServletRequest request) {

        setLocale(request);

        return currentUrl(request);
    }

}
