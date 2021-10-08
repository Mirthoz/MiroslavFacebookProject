package com.example.miroslavfacebookproject.controller;
import com.example.miroslavfacebookproject.dto.UserRegistrationDto;
import com.example.miroslavfacebookproject.entity.Users;
import com.example.miroslavfacebookproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/registration")
    public String registration() {return "registration";}

    @GetMapping("/login")
    public String login() {return "login";}

    @GetMapping("/profile")
    public String profile() {return "profile";}

    @GetMapping("/forgot_password")
    public String forgotPassword() {return "forgot_password";}

@Autowired
public UsersRepository usersRepository;
    public Users user;

    @GetMapping("/registration/new")
    public String newPerson(@ModelAttribute("person") Users newUser){
        return "registration";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("person") Users newUser, UserRegistrationDto nuser){
        newUser = new Users(nuser.getEmail(), nuser.getPassword(), nuser.getName(), Integer.toString(nuser.getAge()));
        usersRepository.save(newUser);
        return "redirect:http://localhost/phpmyadmin/index.php?route=/sql&db=facebook_project_db&table=users&pos=0";
    }
}