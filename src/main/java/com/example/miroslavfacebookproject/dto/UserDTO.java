package com.example.miroslavfacebookproject.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserDTO {

    private Long id;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String surname;

    @NotBlank
    private String avatarURL;

    @NotBlank
    @Min(14)
    private String age;

    private boolean emailPrivacy = false;
    private boolean agePrivacy = false;
    private boolean postsAndImagesPrivacy = false;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isEmailPrivacy() {
        return emailPrivacy;
    }

    public void setEmailPrivacy(boolean emailPrivacy) {
        this.emailPrivacy = emailPrivacy;
    }

    public boolean isAgePrivacy() {
        return agePrivacy;
    }

    public void setAgePrivacy(boolean agePrivacy) {
        this.agePrivacy = agePrivacy;
    }

    public boolean isPostsAndImagesPrivacy() {
        return postsAndImagesPrivacy;
    }

    public void setPostsAndImagesPrivacy(boolean postsAndImagesPrivacy) {
        this.postsAndImagesPrivacy = postsAndImagesPrivacy;
    }
}
