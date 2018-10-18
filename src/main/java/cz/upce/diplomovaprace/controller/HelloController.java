package cz.upce.diplomovaprace.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

   // @Autowired
   // StudentJdbcRepository studentJdbcRepository;

    @RequestMapping("/sa")
    public String index() {

        return "@aGreetings from Spring Boot!";
    }

}