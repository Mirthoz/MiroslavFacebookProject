package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.exception.InvalidPasswordExcetption;
import com.example.miroslavfacebookproject.exception.InvalidUserException;

public interface UserService {

    User register(RegisterDTO registerDTO);
    String login(String email, String password);
}
