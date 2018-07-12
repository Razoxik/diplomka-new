package cz.upce.diplomovaprace.controller;

import cz.upce.diplomovaprace.entity.Student;
import cz.upce.diplomovaprace.repository.StudentJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    StudentJdbcRepository studentJdbcRepository;

    @RequestMapping("/s")
    public String index() {
        List<Student> listStudentu = (List<Student>) studentJdbcRepository.findAll();
        List<Student> studs = studentJdbcRepository.findByName("Ra");
        return "@aGreetings from Spring Boot!";
    }

}