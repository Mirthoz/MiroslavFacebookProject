package com.example.miroslavfacebookproject.service.implementation;
import com.example.miroslavfacebookproject.service.contract.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private final JavaMailSender javaMailSender;

    public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String resetCode;
    public String userEmail;
    public String userPassword;
    public String userRepeatPassword;

    @Override
    public void sendEmail(String email, String password, String passwordRepeat){
        SimpleMailMessage message = new SimpleMailMessage();
        String uuid = generateCode();
        message.setFrom("${spring.mail.username}");
        message.setTo(email);
        message.setText("http://localhost:8080/reset_password/" + uuid);
        message.setSubject("Password reset");
        javaMailSender.send(message);
        userEmail = email;
        userPassword = password;
        userRepeatPassword = passwordRepeat;
        System.out.println("Mail is sent!");
    }

    @Override
    public String generateCode(){
        UUID uuid = UUID.randomUUID();
        resetCode = uuid.toString();
        return uuid.toString();
    }
}
