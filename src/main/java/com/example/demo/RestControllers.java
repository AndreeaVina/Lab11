package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllers {
    @RequestMapping("/getPersons")
    public String sayHello() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/postPersons")
    public void addPerson() {

    }
}