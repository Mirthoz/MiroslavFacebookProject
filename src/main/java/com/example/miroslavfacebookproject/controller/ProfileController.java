package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.LikeService;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController extends BaseController {

    private final ProfileService profileService;
    private final LikeService likeService;

    public ProfileController(ProfileService profileService,
                             LikeService likeService) {
        this.profileService = profileService;
        this.likeService = likeService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public ModelAndView loginProfile() {
        return redirect("profile");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my_reported_posts")
    public ModelAndView ShowMyReportedPosts(@AuthenticationPrincipal User currentUser, ModelAndView modelAndView) {
        modelAndView.addObject("reported_posts", profileService.takeMyReportedPosts(currentUser.getId()));
        modelAndView.addObject("my_reported_posts");
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public ModelAndView deleteProfile(@AuthenticationPrincipal UserDetails userDetails) {
        profileService.deleteProfile(userDetails);
        return redirect("logout");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ModelAndView profile(@AuthenticationPrincipal User currentUser, Model model) {
        likeService.checkingLikes(currentUser);
        return profileService.sendProfileData(currentUser, model);
    }
}