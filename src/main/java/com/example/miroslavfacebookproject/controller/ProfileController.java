package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.ForgotPasswordDTO;
import com.example.miroslavfacebookproject.service.implementation.EmailSenderService;
import com.example.miroslavfacebookproject.service.implementation.ProfileServiceImpl;
import com.example.miroslavfacebookproject.service.implementation.ResetPasswordService;
import com.example.miroslavfacebookproject.service.implementation.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController extends BaseController {

    private final ProfileServiceImpl profileServiceImpl;
    private final EmailSenderService emailSenderService;
    private final ResetPasswordService resetPasswordService;
    private final UserServiceImpl userServiceImpl;


    public ProfileController(ProfileServiceImpl profileServiceImpl, EmailSenderService emailSenderService, ResetPasswordService resetPasswordService, UserServiceImpl userServiceImpl) {
        this.profileServiceImpl = profileServiceImpl;
        this.emailSenderService = emailSenderService;
        this.resetPasswordService = resetPasswordService;
        this.userServiceImpl = userServiceImpl;
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("follow_the_link")
    public ModelAndView followTheLink() {
        return redirect("follow_the_link");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public ModelAndView deleteProfile(@AuthenticationPrincipal UserDetails userDetails) {
        profileServiceImpl.deleteProfile(userDetails);
        return redirect("logout");
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/forgot_password")
    public ModelAndView forgotPassword() {
        return send("forgot_password");
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/forgot_password")
    public ModelAndView forgotPassword(@ModelAttribute("user") ForgotPasswordDTO forgotPasswordDTO) {
        emailSenderService.sendEmail(forgotPasswordDTO.getEmail(), forgotPasswordDTO.getPassword(), forgotPasswordDTO.getPasswordRepeat());
        return send("follow_the_link");
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("reset_password/{code}")
    public ModelAndView resetPassword(@PathVariable("code") String codeReset) {
        if (resetPasswordService.resetPassword(codeReset)) {
            userServiceImpl.resetUserPassword(emailSenderService.userEmail,
                    emailSenderService.userPassword,
                    emailSenderService.userRepeatPassword);
            return redirect("login");
        } else {
            return redirect("register");
        }
    }
}
