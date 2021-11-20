package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.LikeDTO;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    void likePost(LikeDTO likeDTO, User currentUser);
    void checkingLikes(User currentUser);

}