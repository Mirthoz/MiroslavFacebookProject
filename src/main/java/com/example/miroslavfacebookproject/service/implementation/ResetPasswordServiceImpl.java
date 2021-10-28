package com.example.miroslavfacebookproject.service.implementation;
import com.example.miroslavfacebookproject.service.contract.ResetPasswordService;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {
    private final EmailSenderServiceImpl emailSenderService;

    public ResetPasswordServiceImpl(EmailSenderServiceImpl emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public boolean resetPassword(String code){
        boolean result;
        if (code.equals(emailSenderService.resetCode)){
            result = true;
        }else {
            result = false;
        }
        return result;
    }
}