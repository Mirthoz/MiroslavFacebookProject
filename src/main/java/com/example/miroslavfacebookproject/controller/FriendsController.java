package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FriendsController extends BaseController{

    @PreAuthorize("isAuthenticated()")
    @GetMapping("friends")
    public ModelAndView friends(@AuthenticationPrincipal User user){
        return send("friends");
    }
}
