package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentsController extends BaseController {
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add_comment")
    public ModelAndView addComment(@AuthenticationPrincipal User currentUser){
        System.out.println("Comment test " + currentUser.getUsername() + " " + currentUser.getSurname());
        return redirect("profile");
    }
}
