package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
    void deleteProfile(UserDetails userDetails);
}
