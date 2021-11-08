package com.example.miroslavfacebookproject.dto;

public class SearchUserDTO {

    private Long id;

    private String username;
    SearchUserDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
