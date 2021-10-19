package com.example.miroslavfacebookproject.controller;
import com.example.miroslavfacebookproject.service.implementation.ProfileServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController extends BaseController{

private final ProfileServiceImpl profileServiceImpl;

    public ProfileController(ProfileServiceImpl profileServiceImpl) {
        this.profileServiceImpl = profileServiceImpl;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public ModelAndView deleteProfile(@AuthenticationPrincipal UserDetails userDetails){
        profileServiceImpl.deleteProfile(userDetails);
        return redirect("logout");
    }
}
