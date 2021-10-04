package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.UserDto;
import com.example.miroslavfacebookproject.entity.Users;
import com.example.miroslavfacebookproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    public UsersRepository usersRepository;

    @PostMapping("registration/add")
    public String addNewUser(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String name,
                             @RequestParam String age,
                             Model model){
        Users users = new Users(email, password, name, age);
        usersRepository.save(users);
    return "redirect:http://localhost/phpmyadmin/index.php?route=/sql&db=facebook_project_db&table=users&pos=0";
    }
}
