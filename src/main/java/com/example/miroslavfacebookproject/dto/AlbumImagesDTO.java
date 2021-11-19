package com.example.miroslavfacebookproject.dto;

import com.example.miroslavfacebookproject.entity.AlbumImage;

import java.util.List;

public class AlbumImagesDTO {
    List <AlbumImage> albumImages;

    public AlbumImagesDTO(){}

    public List<AlbumImage> getAlbumImages() {
        return albumImages;
    }

    public void setAlbumImages(List<AlbumImage> albumImages) {
        this.albumImages = albumImages;
    }
}
