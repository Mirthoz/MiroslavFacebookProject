package com.example.miroslavfacebookproject.service.contract;

import org.springframework.mail.javamail.JavaMailSender;

public interface EmailSenderService {
    String generateCode();
    void sendEmail(String email, String password, String passwordRepeat);
}
