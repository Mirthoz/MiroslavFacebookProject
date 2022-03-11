package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface PostService {
    void savePost(PostDTO postDTO, User user) throws IOException;

    void reportPost(Long postId);

    void blockPost(Long postId);

    List<User> takeUserFriends(Long currentUserId);
}
