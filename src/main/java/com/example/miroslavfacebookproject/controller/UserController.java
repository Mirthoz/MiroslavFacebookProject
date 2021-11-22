package com.example.miroslavfacebookproject.controller;
import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.dto.UserDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import com.example.miroslavfacebookproject.service.contract.RegistrationService;
import com.example.miroslavfacebookproject.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController extends BaseController {

    private final UserServiceImpl userServiceImpl;
    private final ProfileService profileService;
    private final RegistrationService registrationService;

    @Autowired
    public UserController(UserServiceImpl userService,
                          ProfileService profileService,
                          RegistrationService registrationService) {
        this.userServiceImpl = userService;
        this.profileService = profileService;
        this.registrationService = registrationService;}

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("user") RegisterDTO registerDTO) {
        return send("register");
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/register")
    public ModelAndView register(@Validated @ModelAttribute("user") RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
    return registrationService.registration(registerDTO, result, redirectAttributes);}

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public ModelAndView loginProfile(){
        return redirect("profile");
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/login")
    public ModelAndView login() {
        return send("login");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ModelAndView profile(@AuthenticationPrincipal UserDetails userDetails, @AuthenticationPrincipal User currentUser, Model model) {
    return profileService.sendProfileData(userDetails, currentUser, model);}

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/search_user")
    public ModelAndView searchUserByName(@AuthenticationPrincipal User user, @ModelAttribute("username") SearchUserDTO searchUserDTO, Model model){
        return profileService.searchUserByName(user, searchUserDTO, model);}

    @PreAuthorize("isAuthenticated()")
    @PostMapping("add_post")
    public ModelAndView addPost(@ModelAttribute("post_add") PostDTO postDTO, @AuthenticationPrincipal User user){
    userServiceImpl.savePost(postDTO, user);
        return redirect("profile");}

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/change_my_information")
    public ModelAndView changeUserData(){
       return send("change_my_information");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change")
    public ModelAndView changeUserInfo(@ModelAttribute("user") UserDTO userDTO, @AuthenticationPrincipal UserDetails userDetails) {
        userServiceImpl.changeUserInformation(userDTO, userDetails);
        return send("login");}
}
