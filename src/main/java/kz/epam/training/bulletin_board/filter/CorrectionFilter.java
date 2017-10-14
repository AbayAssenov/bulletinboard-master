package kz.epam.training.bulletin_board.filter;

import kz.epam.training.bulletin_board.constant.LoggerConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Abay Assenov
 */

public class CorrectionFilter implements Filter {

    private final Logger LOGGER = Logger.getLogger(CorrectionFilter.class);

    public void destroy() {
        //This method is not used
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        try {

            request.setCharacterEncoding("UTF-8");// Set type encoding of request data
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");// Set default content type of response
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // Forbid safe cache for HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // Forbid safe cache for HTTP 1.0.
            response.setHeader("Expires", "0"); // // Forbid safe cache for Proxies.

            chain.doFilter(req, resp);

        } catch (IOException | ServletException e) {

            LOGGER.error(LoggerConstant.ERROR_FILTER, e);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        //This method is not used
    }
}