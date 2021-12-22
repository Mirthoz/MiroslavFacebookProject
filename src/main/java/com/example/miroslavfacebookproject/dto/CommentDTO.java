package com.example.miroslavfacebookproject.dto;

public class CommentDTO {
    String commentText;
    Long commentatorId;

    CommentDTO(){};

    public CommentDTO(String commentText, Long commentatorId) {
        this.commentText = commentText;
        this.commentatorId = commentatorId;
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
}
