package cz.upce.diplomovaprace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class UserProfileController {

    @GetMapping("/userProfile")
    public String renderMap(Map<String, Object> model) {


        return "theme/examples/user";
    }
}
