package com.example.miroslavfacebookproject.controller;
import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.dto.UserDTO;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.AutoLoginService;
import com.example.miroslavfacebookproject.service.contract.LikeService;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
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

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController extends BaseController {

    private final UserServiceImpl userServiceImpl;
    private final PostRepository postRepository;
    private final LikeService likeService;
    private final AutoLoginService autoLoginService;
    private final ProfileService profileService;

    @Autowired
    public UserController(UserServiceImpl userService,
                          PostRepository postRepository,
                          LikeService likeService,
                          AutoLoginService autoLoginService,
                          ProfileService profileService) {
        this.userServiceImpl = userService;
        this.postRepository = postRepository;
        this.likeService = likeService;
        this.autoLoginService = autoLoginService;
        this.profileService = profileService;
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("user") RegisterDTO registerDTO) {
        return send("register");
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/register")
    public ModelAndView register(@Validated @ModelAttribute("user") RegisterDTO registerDTO,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", registerDTO);
            return redirect("register", "user", registerDTO);
        }
        userServiceImpl.registration(registerDTO);
        autoLoginService.autoLogin(registerDTO);
        return redirect("profile");
    }

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
        likeService.checkingLikes(currentUser);
        List<Post> posts = postRepository.findAll();
        posts = posts.stream().sorted(((o1, o2) -> o2.getDate().compareTo(o1.getDate()))).collect(Collectors.toList());
        model.addAttribute("posts", posts);

    return userServiceImpl.takeUserData(userDetails);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/search_user")
    public ModelAndView searchUserByName(@AuthenticationPrincipal User user, @ModelAttribute("username") SearchUserDTO searchUserDTO, Model model){
        return profileService.searchUserByName(user, searchUserDTO, model);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("add_post")
    public ModelAndView addPost(@ModelAttribute("post_add") PostDTO postDTO, @AuthenticationPrincipal User user){
    userServiceImpl.savePost(postDTO, user);
        return redirect("profile");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/change_my_information")
    public ModelAndView changeUserData(){
       return send("change_my_information");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/change")
    public ModelAndView changeUserInfo(@ModelAttribute("user") UserDTO userDTO, @AuthenticationPrincipal UserDetails userDetails) {
        userServiceImpl.changeUserInformation(userDTO, userDetails);
        return send("login");
    }
}
