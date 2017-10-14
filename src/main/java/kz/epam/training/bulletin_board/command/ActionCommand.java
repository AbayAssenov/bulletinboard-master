package kz.epam.training.bulletin_board.command;

  import javax.servlet.http.HttpServletRequest;

/**
 * @author Abay Assenov
 */

public interface ActionCommand {

        String execute(HttpServletRequest request);
    }

