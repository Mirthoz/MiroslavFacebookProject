package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class PostController extends BaseController{
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("add_post")
    public ModelAndView addPost(@ModelAttribute("post_add") PostDTO postDTO, @AuthenticationPrincipal User user) throws IOException {
        postService.savePost(postDTO, user);
        return redirect("profile");}
}
