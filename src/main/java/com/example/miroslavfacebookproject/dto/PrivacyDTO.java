package com.example.miroslavfacebookproject.dto;

public class PrivacyDTO {
    private boolean emailPrivacy = false;
    private boolean agePrivacy = false;
    private boolean postsAndImagesPrivacy = false;

    public PrivacyDTO(){}

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
