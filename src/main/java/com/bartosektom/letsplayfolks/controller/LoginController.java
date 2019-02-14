package com.bartosektom.letsplayfolks.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Value("${application.message:Hello World}")
    private String helloMessage;

    @GetMapping("/loginS")
    public String login() {
        return "login";
    }

    @PostMapping("/loginS")
    public String login2() {
        return "login";
    }
}
