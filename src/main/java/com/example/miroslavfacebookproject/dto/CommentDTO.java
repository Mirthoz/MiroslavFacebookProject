package com.example.miroslavfacebookproject.dto;

public class CommentDTO {
    String commentText;
    Long commentatorID;

    CommentDTO(){};

    public CommentDTO(String commentText, Long commentatorID) {
        this.commentText = commentText;
        this.commentatorID = commentatorID;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Long getCommentatorID() {
        return commentatorID;
    }

    public void setCommentatorID(Long commentatorID) {
        this.commentatorID = commentatorID;
    }
}
