package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.RegisterDTO;
import org.springframework.stereotype.Service;

@Service
public interface AutoLoginService {

    void autoLogin(RegisterDTO registerDTO);
}