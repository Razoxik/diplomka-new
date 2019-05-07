package com.bartosektom.letsplayfolks.controller;

import com.bartosektom.letsplayfolks.constants.ActiveTabConstants;
import com.bartosektom.letsplayfolks.constants.CommonConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class NewsController {

    private static final String NEWS_VIEW_NAME = "/news/news";

    @GetMapping("/news")
    public ModelAndView renderNews(@RequestParam(value = CommonConstants.SUCCESS_MESSAGE, required = false) String successMessage,
                                   Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.NEWS);
        model.put(CommonConstants.SUCCESS_MESSAGE, successMessage);

        return new ModelAndView(NEWS_VIEW_NAME, model);
    }
}
