package kz.epam.training.bulletin_board.command.impl.handler;

import kz.epam.training.bulletin_board.command.ActionCommand;
import kz.epam.training.bulletin_board.constant.FormConstant;
import kz.epam.training.bulletin_board.constant.LocaleConstant;
import kz.epam.training.bulletin_board.constant.ParameterConstant;
import kz.epam.training.bulletin_board.model.location.Region;
import kz.epam.training.bulletin_board.resource.ConfigurationManager;
import kz.epam.training.bulletin_board.util.CurrentLocale;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Abay Assenov
 */
public class Location implements ActionCommand {

    public String execute(HttpServletRequest request) {

        int regionId = Integer.valueOf(request.getParameter(ParameterConstant.ID_REGION));

        if (regionId > ParameterConstant.NEGATIVE_VALUE) {

            String locale = request.getSession().getAttribute(LocaleConstant.JSTL_FMT_LOCAL) != null ? // Take current locale
                    (String) request.getSession().getAttribute(LocaleConstant.JSTL_FMT_LOCAL)
                    : CurrentLocale.defineLocale(request.getLocale().toString());

            Region region = (Region) ((List) request.getServletContext().getAttribute(ParameterConstant.LOCATION + locale)).get(regionId);// Take specific region by id

            request.setAttribute(ParameterConstant.DISTRICT, region.getDistricts());
        }

        return ConfigurationManager.getProperty(FormConstant.FORM_BODY_DISTRICT_LIST);
    }
}