package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.enums.ActiveTabConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@SessionAttributes(ActiveTabConstants.ACTIVE_TAB)
public class NewsController {

    @GetMapping(value = {  "/news" })
    public String welcomeDefault(Map<String, Object> model) {
        model.put(ActiveTabConstants.ACTIVE_TAB, ActiveTabConstants.NEWS);
        return "news";
    }


}
