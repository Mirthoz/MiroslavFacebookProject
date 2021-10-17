package com.example.miroslavfacebookproject.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    @Min(14)
    private String age;

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
}