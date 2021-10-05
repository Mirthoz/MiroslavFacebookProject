package com.example.miroslavfacebookproject.repository;
import com.example.miroslavfacebookproject.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository <Posts, Long>{
}
