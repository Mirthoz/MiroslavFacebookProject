package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.controller.BaseController;
import com.example.miroslavfacebookproject.service.contract.AutoLoginService;
import com.example.miroslavfacebookproject.service.contract.ResetPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ResetPasswordServiceImpl extends BaseController implements ResetPasswordService {
    private final EmailSenderServiceImpl emailSenderService;
    private final UserServiceImpl userServiceImpl;
    private final AutoLoginService autoLoginService;

    public ResetPasswordServiceImpl(EmailSenderServiceImpl emailSenderService, UserServiceImpl userServiceImpl, AutoLoginService autoLoginService) {
        this.emailSenderService = emailSenderService;
        this.userServiceImpl = userServiceImpl;
        this.autoLoginService = autoLoginService;
    }

    @Override
    public ModelAndView resetPassword(String code, ModelAndView modelAndView) {
        boolean comparisonOfCodes = code.equals(emailSenderService.resetCode);
        if (comparisonOfCodes) {
            userServiceImpl.resetUserPassword(emailSenderService.userEmail,
                    emailSenderService.userPassword,
                    emailSenderService.userRepeatPassword);
            autoLoginService.autoLogin(emailSenderService.takeRegisterDTO());

            return redirect("profile");
        }
        return redirect("register");
    }
}