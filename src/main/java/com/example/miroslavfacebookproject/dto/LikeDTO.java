package com.example.miroslavfacebookproject.dto;

import com.example.miroslavfacebookproject.entity.Post;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class LikeDTO {
    public LikeDTO(){}

    private Long postId;

    @ManyToOne(targetEntity = Post.class, fetch = FetchType.LAZY)
    private Post post;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
