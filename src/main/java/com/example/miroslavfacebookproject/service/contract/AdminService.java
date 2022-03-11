package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    void makeAdmin(Long targetUserId);

    void makeUser(Long targetUserId);

    List<Post> showReportedPosts();
}
