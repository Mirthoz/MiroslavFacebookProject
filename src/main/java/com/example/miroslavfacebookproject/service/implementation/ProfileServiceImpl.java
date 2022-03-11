package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.controller.BaseController;
import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.dto.UserDTO;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.PostService;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl extends BaseController implements ProfileService {
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;
    private final PostRepository postRepository;
    private final PostService postService;

    public ProfileServiceImpl(UserRepository userRepository,
                              UserServiceImpl userServiceImpl,
                              PostRepository postRepository,
                              PostService postService) {

        this.userRepository = userRepository;
        this.userServiceImpl = userServiceImpl;
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @Override
    public void deleteProfile(UserDetails userName) {
        User user = userServiceImpl.takeUserByUserName(userName.getUsername());
        userRepository.delete(user);
    }

    @Override
    public ModelAndView searchUserByName(User user, SearchUserDTO searchUserDTO, Model model) {
        List<UserDTO> findUsers = userServiceImpl.findByName(searchUserDTO.getUsername());
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        userDTO.setId(user.getId());
        userDTO.setAvatarURL(user.getAvatar().getAvatarURL());
        userDTO.setSurname(user.getSurname());
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("find_users", findUsers);
        return send("profile");
    }

    private List<Post> takeUserPosts(User currentUser){
        List<Post> allPosts = postRepository.findAll();
        List<Post> userPosts = allPosts.stream().filter(p -> p.getPoster().getId().equals(currentUser.getId())).collect(Collectors.toList());
        List<User> userFriends = postService.takeUserFriends(currentUser.getId());
        List<Post> friendsPosts = allPosts.stream().filter(post -> userFriends.stream().anyMatch(user -> post.getPoster().equals(user))).collect(Collectors.toList());
        List<Post> userProfilePosts = new ArrayList<>();
        userProfilePosts.addAll(userPosts);
        userProfilePosts.addAll(friendsPosts);
        userProfilePosts = userProfilePosts.stream().sorted(((o1, o2) -> o2.getDate().compareTo(o1.getDate()))).collect(Collectors.toList());
        userProfilePosts = userProfilePosts.stream().filter(post -> !post.getStatus().equals("BLOCKED")).collect(Collectors.toList());
        return userProfilePosts;
    }

    @Override
    public ModelAndView sendProfileData(User currentUser, Model model) {
        List<Post> userProfilePosts = takeUserPosts(currentUser);
        model.addAttribute("posts", userProfilePosts);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(currentUser.getEmail());
        userDTO.setUsername(currentUser.getUsername());
        userDTO.setAge(currentUser.getAge());
        userDTO.setSurname(currentUser.getSurname());
        userDTO.setAvatarURL(currentUser.getAvatar().getAvatarURL());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDTO", userDTO);
        return modelAndView;
    }

    @Override
    public List<Post> takeMyReportedPosts(Long currentUserId) {
        return postRepository.findAll().stream().filter(post -> post.getPoster().getId().equals(currentUserId) && post.getStatus().equals("REPORTED")).collect(Collectors.toList());
    }
}