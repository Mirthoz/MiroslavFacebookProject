package com.example.miroslavfacebookproject.controller;
import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.dto.UserDTO;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.LikeService;
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
    private final UserRepository userRepository;
    private final LikeService likeService;

    @Autowired
    public UserController(UserServiceImpl userService, PostRepository postRepository, UserRepository userRepository, LikeService likeService) {
        this.userServiceImpl = userService;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeService = likeService;
    }

    @PreAuthorize("!isAuthenticated()")
    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("user") RegisterDTO registerDTO) {
        return send("register");
    }

    @PreAuthorize("!isAuthenticated()")
    @PostMapping("/register")
    public ModelAndView register(@Validated @ModelAttribute("user") RegisterDTO registerDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute("user", registerDTO);
            return redirect("register", "user", registerDTO);
        }
        userServiceImpl.register(registerDTO);
        return redirect("login");
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
        likeService.checkLikes(currentUser);
        List<Post> posts = postRepository.findAll();
        posts = posts.stream().sorted(((o1, o2) -> o2.getDate().compareTo(o1.getDate()))).collect(Collectors.toList());
        model.addAttribute("posts", posts);

    return userServiceImpl.getUserData(userDetails);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/search_user")
    public ModelAndView searchUserByName(@AuthenticationPrincipal User user, @ModelAttribute("username") SearchUserDTO searchUserDTO, Model model){
        List<UserDTO> findUsers = userServiceImpl.findByName(searchUserDTO.getUsername());
        List<Post> posts = postRepository.findAll();
        posts = posts.stream().sorted(((o1, o2) -> o2.getDate().compareTo(o1.getDate()))).collect(Collectors.toList());
        model.addAttribute("posts", posts);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setId(user.getId());
        userDTO.setAvatarURL(user.getAvatar().getAvatarURL());

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("find_users", findUsers);

        return send("profile");
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
        userServiceImpl.changeData(userDTO, userDetails);
        return send("login");
    }
}
