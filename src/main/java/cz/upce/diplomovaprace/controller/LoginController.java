package cz.upce.diplomovaprace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/loginS")
    public String login() {
        return "login";
    }

    @PostMapping("/loginS")
    public String login2() {
        return "login";
    }
}
