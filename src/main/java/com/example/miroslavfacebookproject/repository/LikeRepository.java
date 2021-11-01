package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.Like;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByPostAndUser(Post post, User user);
    Long existsByUserId(User userId);
    Like findFirstByPostAndUser(Post post, User currentUser);
}