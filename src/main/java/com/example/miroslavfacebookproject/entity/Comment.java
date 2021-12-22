package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String commentText;

    @Column(name = "commentator_id")
    private Long commentatorId;

    @ManyToMany
    private List<Post> posts;

    public Comment(){}

    public Comment(String commentText, Long commentatorId) {
        this.commentText = commentText;
        this.commentatorId = commentatorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(Long commentatorId) {
        this.commentatorId = commentatorId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
