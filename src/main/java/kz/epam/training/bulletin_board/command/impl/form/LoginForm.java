package kz.epam.training.bulletin_board.command.impl.form;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.FormConstant;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Abay Assenov
 */

public class LoginForm implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        return ConfigurationManager.getProperty(FormConstant.FORM_LOGIN);
    }
}
