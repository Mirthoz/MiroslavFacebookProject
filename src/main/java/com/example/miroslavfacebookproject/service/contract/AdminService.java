package com.example.miroslavfacebookproject.service.contract;

import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    void makeAdmin(Long targetUserId);
    void makeUser(Long targetUserId);
}
