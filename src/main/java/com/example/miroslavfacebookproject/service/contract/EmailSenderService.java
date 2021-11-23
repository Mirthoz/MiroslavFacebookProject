package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.RegisterDTO;

public interface EmailSenderService {
    RegisterDTO takeRegisterDTO();
    String generateCode();
    void sendEmail(String email, String password, String passwordRepeat);
}
