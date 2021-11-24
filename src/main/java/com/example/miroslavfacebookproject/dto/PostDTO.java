package com.example.miroslavfacebookproject.dto;

import com.example.miroslavfacebookproject.entity.Like;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

public class PostDTO {
    private String postText;
    private MultipartFile postImage = null;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Set<Like> likes;

    public PostDTO(){}

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public MultipartFile getPostImage() {
        return postImage;
    }

    public void setPostImage(MultipartFile postImage) {
        this.postImage = postImage;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }
}