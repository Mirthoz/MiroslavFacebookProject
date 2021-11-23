package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface PostService {
    void savePost(PostDTO postDTO, User user) throws IOException;
}
