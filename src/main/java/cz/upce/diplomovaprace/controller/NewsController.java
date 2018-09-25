package cz.upce.diplomovaprace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class NewsController {

    @GetMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("activeTab", "News");

        return "news";
    }

    @GetMapping("/news")
    public String welcome2(Map<String, Object> model) {
        model.put("activeTab", "News");

        return "news";
    }
}
