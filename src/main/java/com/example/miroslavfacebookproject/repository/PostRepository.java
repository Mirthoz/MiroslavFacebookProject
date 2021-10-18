package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findFirstById(Long id);
}
