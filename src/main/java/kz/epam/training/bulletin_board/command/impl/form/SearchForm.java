package kz.epam.training.bulletin_board.command.impl.form;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.command.impl.handler.Search;
import kz.epam.training.bulletin_board.constant.FormConstant;
import kz.epam.training.bulletin_board.constant.LocaleConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import kz.epam.training.bulletin_board.util.CurrentLocale;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Abay Assenov
 */

public class SearchForm implements ActionCommand {


    @Override
    public String execute(HttpServletRequest request) {

        String locale = request.getSession().getAttribute(LocaleConstant.JSTL_FMT_LOCAL) != null ? // Take current locale
                (String) request.getSession().getAttribute(LocaleConstant.JSTL_FMT_LOCAL)
                : CurrentLocale.defineLocale(request.getLocale().toString());

        request.setAttribute(ParameterConstant.HEADINGS,
                request.getServletContext().getAttribute(ParameterConstant.HEADINGS + locale)); //Set headings by locale

        request.setAttribute(ParameterConstant.LOCATION,
                request.getServletContext().getAttribute(ParameterConstant.LOCATION + locale)); //Set location by locale

        Search search = new Search();

        search.execute(request);

        return ConfigurationManager.getProperty(FormConstant.FORM_SEARCH_ADVERT);
    }
}
