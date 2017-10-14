package kz.epam.training.bulletin_board.tag;

/**
 * @author Abay Assenov
 *         9/24/2017
 */

import kz.epam.training.bulletin_board.constant.LoggerConstant;
import kz.epam.training.bulletin_board.constant.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CommonLink extends TagSupport{

    private final Logger LOGGER = Logger.getLogger(CommonLink.class);

    @Override
    public int doStartTag() throws JspException {

        try{

            JspWriter out = pageContext.getOut();
            out.write(PageConstant.COMMON_LINKS);

        }catch(IOException e){

            LOGGER.error(LoggerConstant.ERROR_TAG_LIB, e);
        }
        return SKIP_BODY;
    }
}
