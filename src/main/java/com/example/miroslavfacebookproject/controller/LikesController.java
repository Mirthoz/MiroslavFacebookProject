package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.LikeDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.LikeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LikesController extends BaseController {
    public static Long publicProfileId;
    private final LikeService likeService;

    public LikesController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/likePost")
    public ModelAndView likePost(@ModelAttribute LikeDTO likeDTO, @AuthenticationPrincipal User user) {
        likeService.likePost(likeDTO, user);
        return redirect("profile");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/likePublicPost")
    public ModelAndView likePublicPost(@ModelAttribute LikeDTO likeDTO, @AuthenticationPrincipal User user) {
        likeService.likePost(likeDTO, user);
        return redirect("public_profile?userId=" + publicProfileId);
    }
}
