package com.example.miroslavfacebookproject.controller;
import com.example.miroslavfacebookproject.dto.ForgotPasswordDTO;
import com.example.miroslavfacebookproject.service.implementation.EmailSenderService;
import com.example.miroslavfacebookproject.service.implementation.ProfileServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController extends BaseController{

private final ProfileServiceImpl profileServiceImpl;
private final EmailSenderService emailSenderService;

    public ProfileController(ProfileServiceImpl profileServiceImpl, EmailSenderService emailSenderService) {
        this.profileServiceImpl = profileServiceImpl;
        this.emailSenderService = emailSenderService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public ModelAndView deleteProfile(@AuthenticationPrincipal UserDetails userDetails){
        profileServiceImpl.deleteProfile(userDetails);
        return redirect("logout");
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/forgot_password")
    public ModelAndView forgotPassword(){
        return send("forgot_password");
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/forgot_password")
    public ModelAndView forgotPassword(@ModelAttribute("user") ForgotPasswordDTO forgotPasswordDTO){
        return send("forgot_password");
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/send_email")
    public ModelAndView sendEmail(){
        emailSenderService.sendEmail("miroslav.92@mail.ru", "Subject", "Go to work!!!");
        return send("registration");
    }
}
