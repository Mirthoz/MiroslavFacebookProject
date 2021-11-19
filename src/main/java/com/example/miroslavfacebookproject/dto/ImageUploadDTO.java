package com.example.miroslavfacebookproject.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadDTO {
    private MultipartFile image;
    public String imageDescription;

    ImageUploadDTO(){}

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }
}
