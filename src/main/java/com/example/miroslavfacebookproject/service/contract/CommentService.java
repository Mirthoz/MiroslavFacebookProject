package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.CommentDTO;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void addComment(CommentDTO commentDTO, User currentUser);
}
