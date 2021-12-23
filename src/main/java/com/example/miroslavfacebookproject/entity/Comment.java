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

    @Column(name = "commentator_name_and_surname")
    private String commentatorNameAndSurname;

    @ManyToMany
    private List<Post> posts;

    public Comment(){}

    public Comment(String commentText, String commentatorNameAndSurname) {
        this.commentText = commentText;
        this.commentatorNameAndSurname = commentatorNameAndSurname;
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

    public String getCommentatorNameAndSurname() {
        return commentatorNameAndSurname;
    }

    public void setCommentatorNameAndSurname(String commentatorNameAndSurname) {
        this.commentatorNameAndSurname = commentatorNameAndSurname;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
