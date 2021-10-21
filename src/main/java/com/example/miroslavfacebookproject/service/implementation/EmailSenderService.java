package com.example.miroslavfacebookproject.service.implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailSenderService {

    @Autowired
    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String resetCode;

    public void sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        String uuid = generateCode();
        message.setFrom("${spring.mail.username}");
        message.setTo("miroslav.92@mail.ru");
        message.setText("http://localhost:8080/reset_password/" + uuid);
        message.setSubject("Password reset");
        javaMailSender.send(message);

        System.out.println("Mail is sent!");
        System.out.println(uuid);
    }

    public String generateCode(){
        UUID uuid = UUID.randomUUID();
        resetCode = uuid.toString();
        return uuid.toString();
    }
}
