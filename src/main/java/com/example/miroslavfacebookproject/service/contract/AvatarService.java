package com.example.miroslavfacebookproject.service.contract;

import org.springframework.stereotype.Service;

@Service
public interface AvatarService {

    void addAvatar(Long currentUser, String avatar);
}
