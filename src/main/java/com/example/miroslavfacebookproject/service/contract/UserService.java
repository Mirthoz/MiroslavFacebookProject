package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;
@Service
public interface UserService {
    User register(RegisterDTO registerDTO);
    String login(String email, String password);
    User getUserByUsername(String username);
}
