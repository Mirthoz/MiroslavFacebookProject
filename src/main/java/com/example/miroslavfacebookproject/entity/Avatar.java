package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "avatar")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "avatar_url")
    private String avatarURL = "https://firebasestorage.googleapis.com/v0/b/facebookproject-3ac7a.appspot.com/o/64098da3-c03e-4d39-b7ed-51a1032bae6f.jpg?alt=media&token=64098da3-c03e-4d39-b7ed-51a1032bae6f.jpg";

    public Avatar(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }
}
