package kz.epam.training.bulletin_board.command.factory;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.command.client.CommandEnum;
import kz.epam.training.bulletin_board.command.impl.form.MainForm;
import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Abay Assenov
 */

public class ActionFactory {

    private final Logger LOGGER = Logger.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request) {

        ActionCommand current = new MainForm();// default command is main form

        String action = request.getParameter(ParameterConstant.COMMAND);// take command name

        if (action == null || action.isEmpty()) { //if empty command

            return current;
        }

        try {

            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());

            current = currentEnum.getCurrentCommand();

        } catch (IllegalArgumentException e) {

            LOGGER.error(LoggerConstant.ERROR_ACTION_FACTORY, e);

        }

        return current;
    }
}