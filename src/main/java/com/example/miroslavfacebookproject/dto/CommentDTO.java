package com.example.miroslavfacebookproject.dto;

public class CommentDTO {
    String commentText;
    Long currentPostId;
    String commentatorNameAndSurname;

    CommentDTO(){};

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getCurrentPostId() {
        return currentPostId;
    }

    public void setCurrentPostId(Long currentPostId) {
        this.currentPostId = currentPostId;
    }

    public String getCommentatorNameAndSurname() {
        return commentatorNameAndSurname;
    }

    public void setCommentatorNameAndSurname(String commentatorNameAndSurname) {
        this.commentatorNameAndSurname = commentatorNameAndSurname;
    }
}
