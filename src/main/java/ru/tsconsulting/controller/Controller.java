package ru.tsconsulting.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/hello")
    public String showHello(){
        return "test";
    }
}
