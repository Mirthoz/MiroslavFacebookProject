package com.example.miroslavfacebookproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @PostMapping("/registration")
    public String registration(){
        return "registration.html";
    }
}
