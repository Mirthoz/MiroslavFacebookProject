package com.example.miroslavfacebookproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessDeniedController extends BaseController{

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/unauthorized")
    public ModelAndView unauthorized(){
    return send("unauthorized");
    }
}
