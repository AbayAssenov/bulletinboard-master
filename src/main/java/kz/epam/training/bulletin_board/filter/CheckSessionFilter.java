package kz.epam.training.bulletin_board.filter;

import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.constant.RoleConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Abay Assenov
 */

public class CheckSessionFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(CheckSessionFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        //This method is not used
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();

        if (session.getAttribute(RoleConstant.ROLE) == null) { // Set role for guest
            session.setAttribute(RoleConstant.ROLE, RoleConstant.GUEST);
        }
        try {

            chain.doFilter(req, resp);

        } catch (IOException | ServletException e) {

            LOGGER.error(LoggerConstant.ERROR_FILTER, e);

        }
    }

    @Override
    public void destroy() {
        //This method is not used
    }
}
