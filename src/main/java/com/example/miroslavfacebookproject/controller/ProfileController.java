package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import com.example.miroslavfacebookproject.service.contract.RegistrationService;
import com.example.miroslavfacebookproject.service.contract.ResetPasswordService;
import com.example.miroslavfacebookproject.service.implementation.EmailSenderServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController extends BaseController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {

        this.profileService = profileService;
       }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public ModelAndView loginProfile(){
        return redirect("profile");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public ModelAndView deleteProfile(@AuthenticationPrincipal UserDetails userDetails) {
        profileService.deleteProfile(userDetails);
        return redirect("logout");}

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ModelAndView profile(@AuthenticationPrincipal UserDetails userDetails, @AuthenticationPrincipal User currentUser, Model model) {
        return profileService.sendProfileData(userDetails, currentUser, model);}
}