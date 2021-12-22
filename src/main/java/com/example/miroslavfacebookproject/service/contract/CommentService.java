package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void addComment(String text, User currentUser);
}
