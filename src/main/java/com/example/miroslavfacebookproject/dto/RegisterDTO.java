package com.example.miroslavfacebookproject.dto;

import javax.validation.constraints.NotBlank;

public class RegisterDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String surname;

    @NotBlank
    private String age;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordRepeat;

    private boolean emailPrivacy = false;
    private boolean agePrivacy = false;
    private boolean postsAndImagesPrivacy = false;

    public RegisterDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
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
