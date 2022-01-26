package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.PrivacyDTO;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface PrivacyService {
    PrivacyDTO createPrivacyDTO(User user);
}
