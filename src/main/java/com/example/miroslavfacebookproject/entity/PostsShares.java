package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "post_shares")
public class PostsShares {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(name = "user_id")
    private Long userId;

    public PostsShares(){}

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
