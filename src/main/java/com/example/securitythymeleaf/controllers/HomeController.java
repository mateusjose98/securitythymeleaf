package com.example.securitythymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {


    @GetMapping
    public String home(){
        return  "home";
    }

    @GetMapping("/adm")
    public String administrador(){
        return "adm";
    }

    @GetMapping("/publica")
    public String publica(){
        return "publica";
    }
}
