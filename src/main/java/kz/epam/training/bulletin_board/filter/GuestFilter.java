package kz.epam.training.bulletin_board.filter;

import kz.epam.training.bulletin_board.constant.FormConstant;
import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.constant.RoleConstant;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Abay Assenov
 */

public class GuestFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(GuestFilter.class);

    public void destroy() {
        //This method is not used
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) req;

        String role = (String) request.getSession().getAttribute(RoleConstant.ROLE);

        try{

        if (RoleConstant.GUEST.equals(role)) { // Redirect to the default page if it is not guest (redirect to main servlet)

            chain.doFilter(req, resp);
        }else{

            String  page = ConfigurationManager.getProperty(FormConstant.FORM_MAIN);

            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
        }catch (ServletException|IOException e){

            LOGGER.error(LoggerConstant.ERROR_FILTER, e);

        }
    }

    public void init(FilterConfig config) throws ServletException {
        //This method is not used
    }

}
