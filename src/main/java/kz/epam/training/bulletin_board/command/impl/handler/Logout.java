package kz.epam.training.bulletin_board.command.impl.handler;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.command.impl.form.MainForm;
import kz.epam.training.bulletin_board.constant.LocaleConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Abay Assenov
 */

public class Logout implements ActionCommand {

    /** Kills current session, but safe current locale*/
    @Override
    public String execute(HttpServletRequest request) {

        String locale = (String) request.getSession().getAttribute(LocaleConstant.JSTL_FMT_LOCAL); //Takes current locale

        request.getSession().invalidate();// Kills current(old) session

        request.getSession().setAttribute(LocaleConstant.JSTL_FMT_LOCAL, locale); //Sets current locale

        return new MainForm().execute(request);
    }
}