package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.dto.UserDTO;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User registration(RegisterDTO registerDTO);
    String resetLogin(String email, String password);
    User takeUserByUserName(String username);
    List<UserDTO> findByName(String name);

}
