package cz.upce.diplomovaprace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class UserController {

    @GetMapping("/user")
    public ModelAndView renderMap(Map<String, Object> model) {


        model.put("activeTab", "User");
        return new ModelAndView("user", model);
    }
}
