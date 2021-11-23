package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "album_images")
public class AlbumImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "image_url")
    public String imageURL;

    @Column(name = "image_description")
    public String imageDescription;

    public AlbumImage() {
    }

    public AlbumImage(String imageURL, String imageDescription) {
        this.imageURL = imageURL;
        this.imageDescription = imageDescription;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }
}