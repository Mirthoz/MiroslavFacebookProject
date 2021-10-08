package com.example.miroslavfacebookproject.controller;
<<<<<<< HEAD
=======

>>>>>>> 4580f71ebaeeb7b8295aade665e95b9314d5277a
import com.example.miroslavfacebookproject.entity.Users;
import com.example.miroslavfacebookproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
=======
>>>>>>> 4580f71ebaeeb7b8295aade665e95b9314d5277a
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

<<<<<<< HEAD
    @GetMapping("/registration")
    public String registration() {return "registration";}

    @GetMapping("/login")
    public String login() {return "login";}

    @GetMapping("/profile")
    public String profile() {return "profile";}

    @GetMapping("/forgot_password")
    public String forgotPassword() {return "forgot_password";}

=======
>>>>>>> 4580f71ebaeeb7b8295aade665e95b9314d5277a
    @Autowired
    public UsersRepository usersRepository;

    @PostMapping("registration/add")
    public String addNewUser(@RequestParam String email,
                             @RequestParam String password,
                             @RequestParam String name,
                             @RequestParam String age,
<<<<<<< HEAD
                             @RequestParam String repeatPassword,
                             Model model){

        if (password.equals(repeatPassword) && Integer.parseInt(age) > 14) {
            Users users = new Users(email, password, name, age);
            usersRepository.save(users);
            return "redirect:http://localhost/phpmyadmin/index.php?route=/sql&db=facebook_project_db&table=users&pos=0";
        }
            return "redirect:http://localhost:8080/registration.html";
=======
                             Model model){
        Users users = new Users(email, password, name, age);
        usersRepository.save(users);
    return "redirect:http://localhost/phpmyadmin/index.php?route=/sql&db=facebook_project_db&table=users&pos=0";
>>>>>>> 4580f71ebaeeb7b8295aade665e95b9314d5277a
    }
}
