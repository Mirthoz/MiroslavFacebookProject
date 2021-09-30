package com.example.miroslavfacebookproject.repository;
import com.example.miroslavfacebookproject.entity.PostsLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsLikesRepository extends JpaRepository <PostsLikes, Long>{
}
