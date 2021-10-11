package com.example.miroslavfacebookproject.dto;

import javax.validation.constraints.*;

public class RegisterDTO {
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty(message = "Repeat password should not be empty")
    private String repeatPassword;

    @Positive(message = "Age should be greater than 14")
    @NotEmpty(message = "Age should not be empty")
    @Min(value = 15, message = "Age should be greater than 14")
    private int age;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public RegisterDTO() {
    }

    public RegisterDTO(String email, String password, String repeatPassword, int age, String name) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.age = age;
        this.name = name;
    }
}