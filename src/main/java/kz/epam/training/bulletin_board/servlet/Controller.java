package kz.epam.training.bulletin_board.servlet;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.command.CommonData;
import kz.epam.training.bulletin_board.command.factory.ActionFactory;
import kz.epam.training.bulletin_board.constant.LocaleConstant;
import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.database.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Abay Assenov
 */

public class Controller extends HttpServlet {

    private final Logger LOGGER = Logger.getLogger(Controller.class);

    @Override
    public void init(ServletConfig config) {

        CommonData commonData = new CommonData(); //Set common data
        commonData.execute(config);

        try {

            super.init(config);

        } catch (ServletException e) {

            LOGGER.error(LoggerConstant.ERROR_IN_SERVLET, e);

        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {

        String actionPage = defineAction(request);

        if (request.getParameter(LocaleConstant.SEND_REDIRECT) != null) {

            sendRedirect(actionPage, response);

        } else {

            sendForward(actionPage, request, response);
        }
    }

    private String defineAction(HttpServletRequest request) {

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);

        return command.execute(request);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().release(); //Close all connection and clear pool connection
        super.destroy();
    }

    private void sendRedirect(String url, HttpServletResponse response) {
        try {

            response.sendRedirect(url);

        } catch (IOException e) {

            LOGGER.error(LoggerConstant.ERROR_ADD_ADVERT, e);
        }
    }

    private void sendForward(String page, HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);

        try {

            dispatcher.forward(request, response);

        } catch (ServletException | IOException e) {

            LOGGER.error(LoggerConstant.ERROR_ADD_ADVERT, e);
        }
    }

}
