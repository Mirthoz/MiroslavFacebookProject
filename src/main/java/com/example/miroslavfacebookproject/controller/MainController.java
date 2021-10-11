package com.example.miroslavfacebookproject.controller;
import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String log() {
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/forgot_password")
    public String forgotPassword() {
        return "forgot_password";
    }

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/register")
    public String newPerson(@ModelAttribute("person") User newUser) {
        return "register";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("person") User newUser, RegisterDTO user) {
        newUser = new User(user.getEmail(), user.getPassword(), user.getName(), Integer.toString(user.getAge()));
        userRepository.save(newUser);
        return "redirect:http://localhost/phpmyadmin/index.php?route=/sql&db=facebook_project_db&table=users&pos=0";
    }
}