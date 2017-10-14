package kz.epam.training.bulletin_board.command.impl.form;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.FormConstant;
import kz.epam.training.bulletin_board.constant.LocaleConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import kz.epam.training.bulletin_board.util.CurrentLocale;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Abay Assenov
 */

public class MainForm implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String locale = request.getSession().getAttribute(LocaleConstant.JSTL_FMT_LOCAL) != null ? // Take current locale
                (String) request.getSession().getAttribute(LocaleConstant.JSTL_FMT_LOCAL)
                : CurrentLocale.defineLocale(request.getLocale().toString());

        request.getSession().setAttribute(ParameterConstant.HEADINGS,
                request.getServletContext().getAttribute(ParameterConstant.HEADINGS + locale)); //Set headings by locale


        return ConfigurationManager.getProperty(FormConstant.FORM_MAIN);
    }
}

