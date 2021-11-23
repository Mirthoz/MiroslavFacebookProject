package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.ForgotPasswordDTO;
import com.example.miroslavfacebookproject.service.contract.EmailSenderService;
import com.example.miroslavfacebookproject.service.contract.ResetPasswordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ForgottenPasswordController extends BaseController {
    private final ResetPasswordService resetPasswordService;
    private final EmailSenderService emailSenderService;

    public ForgottenPasswordController(ResetPasswordService resetPasswordService, EmailSenderService emailSenderService) {
        this.resetPasswordService = resetPasswordService;
        this.emailSenderService = emailSenderService;
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/forgot_password")
    public ModelAndView forgotPassword() {
        return send("forgot_password");
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("reset_password/{code}")
    public ModelAndView resetPassword(@PathVariable("code") String codeReset, ModelAndView modelAndView) {
        return resetPasswordService.resetPassword(codeReset, modelAndView);
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/forgot_password")
    public ModelAndView forgotPassword(@ModelAttribute("user") ForgotPasswordDTO forgotPasswordDTO) {
        emailSenderService.sendEmail(forgotPasswordDTO.getEmail(), forgotPasswordDTO.getPassword(), forgotPasswordDTO.getPasswordRepeat());
        return send("follow_the_link");
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("follow_the_link")
    public ModelAndView followTheLink() {
        return redirect("follow_the_link");
    }
}
