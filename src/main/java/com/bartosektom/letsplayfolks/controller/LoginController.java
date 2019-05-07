package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.constants.CommonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class LoginController {

    private static final String LOGIN_VIEW_NAME = "/login/login";

    /**
     * If you do not specify any mapping this method will resolve all the http request i.e.
     * you can send GET, POST, HEAD, OPTIONS, PUT, PATCH, DELETE, TRACE request to the specified url and it will be resolved.
     * More info about crf tokens: https://stackoverflow.com/questions/5207160/what-is-a-csrf-token-what-is-its-importance-and-how-does-it-work
     *
     * @param error Flag determining if  user login/pass information are correct or otherwise.
     * @param model The model map for view.
     * @return {@link ModelAndView} instance with view and prepared model.
     */
    @RequestMapping(value = {"/", "/login"})
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = CommonConstants.SUCCESS_MESSAGE, required = false) String successMessage,
                              Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.LOGIN);
        model.put(CommonConstants.SUCCESS_MESSAGE, successMessage);
        if (error != null) {
            model.put(CommonConstants.ERROR_MESSAGE, "info.message.loginError");
        }
        return new ModelAndView(LOGIN_VIEW_NAME, model);
    }
}
