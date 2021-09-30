package com.example.miroslavfacebookproject.repository;
import com.example.miroslavfacebookproject.entity.PostsShares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostSharesRepository extends JpaRepository <PostsShares, Long>{
}
