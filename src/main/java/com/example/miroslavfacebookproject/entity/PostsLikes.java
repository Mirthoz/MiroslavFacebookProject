package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "posts_likes")
public class PostsLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(name = "user_id")
    private Long userId;

    public PostsLikes(){}

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
