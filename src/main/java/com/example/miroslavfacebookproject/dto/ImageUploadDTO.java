package com.example.miroslavfacebookproject.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadDTO {
    private MultipartFile image;

    ImageUploadDTO(){}

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}