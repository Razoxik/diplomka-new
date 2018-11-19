package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.constants.ActiveTabConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class NewsController {

    private static final String FROM_LOGIN_REQUEST_PARAM = "fromLogin";
    private static final String FROM_LOGOUT_REQUEST_PARAM = "fromLogout";

    private static final String NEWS_VIEW_NAME = "/news/news";

    @GetMapping("/news")
    public ModelAndView renderNews(@RequestParam(value = FROM_LOGIN_REQUEST_PARAM, required = false) boolean fromLogin,
                                   @RequestParam(value = FROM_LOGOUT_REQUEST_PARAM, required = false) boolean fromLogout,
                                   Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.NEWS);
        model.put(FROM_LOGOUT_REQUEST_PARAM, fromLogout);
        model.put(FROM_LOGIN_REQUEST_PARAM, fromLogin);

        return new ModelAndView(NEWS_VIEW_NAME, model);
    }
}
