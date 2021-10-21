package com.example.miroslavfacebookproject.service.implementation;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService{
    private final EmailSenderService emailSenderService;

    public ResetPasswordService(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public boolean resetPassword(String code){
        boolean result;
        if (code.equals(emailSenderService.resetCode)){
            result = true;
        }else {
            result = false;
        }
        System.out.println(emailSenderService.resetCode);
        return result;
    }
}
