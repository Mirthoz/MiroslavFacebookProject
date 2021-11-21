package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.ForgotPasswordDTO;
import com.example.miroslavfacebookproject.service.contract.AutoLoginService;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import com.example.miroslavfacebookproject.service.implementation.EmailSenderServiceImpl;
import com.example.miroslavfacebookproject.service.implementation.UploadImageServiceImpl;
import com.example.miroslavfacebookproject.service.implementation.ResetPasswordServiceImpl;
import com.example.miroslavfacebookproject.service.implementation.UserServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController extends BaseController {

    private final ProfileService profileService;
    private final EmailSenderServiceImpl emailSenderService;
    private final ResetPasswordServiceImpl resetPasswordService;
    private final UserServiceImpl userServiceImpl;
    private final UploadImageServiceImpl fileService;
    private final AutoLoginService autoLoginService;


    public ProfileController(ProfileService profileService, EmailSenderServiceImpl emailSenderService, ResetPasswordServiceImpl resetPasswordService, UserServiceImpl userServiceImpl, UploadImageServiceImpl fileService, AutoLoginService autoLoginService) {
        this.profileService = profileService;
        this.emailSenderService = emailSenderService;
        this.resetPasswordService = resetPasswordService;
        this.userServiceImpl = userServiceImpl;
        this.fileService = fileService;
        this.autoLoginService = autoLoginService;
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("follow_the_link")
    public ModelAndView followTheLink() {
        return redirect("follow_the_link");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public ModelAndView deleteProfile(@AuthenticationPrincipal UserDetails userDetails) {
        profileService.deleteProfile(userDetails);
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
                    autoLoginService.autoLogin(emailSenderService.takeRegisterDTO());
            return redirect("profile");
        }
        return redirect("register");
    }
}
