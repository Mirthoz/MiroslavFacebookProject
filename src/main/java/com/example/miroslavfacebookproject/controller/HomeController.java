package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.service.implementation.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;

    }

    @GetMapping("/demo")
    public ModelAndView demo() {
        return send("demo");
    }
}
