package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.controller.BaseController;
import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.dto.UserDTO;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.LikeService;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl extends BaseController implements ProfileService {
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;
    private final PostRepository postRepository;
    private final LikeService likeService;

    public ProfileServiceImpl(UserRepository userRepository, UserServiceImpl userServiceImpl, PostRepository postRepository, LikeService likeService) {
        this.userRepository = userRepository;
        this.userServiceImpl = userServiceImpl;
        this.postRepository = postRepository;
        this.likeService = likeService;
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

    @Override
    public ModelAndView sendProfileData(UserDetails userDetails, User currentUser, Model model) {
        likeService.checkingLikes(currentUser);
        List<Post> posts = postRepository.findAll();
        posts = posts.stream().filter(p -> p.getPoster().getId().equals(currentUser.getId())).collect(Collectors.toList());
        posts = posts.stream().sorted(((o1, o2) -> o2.getDate().compareTo(o1.getDate()))).collect(Collectors.toList());
        model.addAttribute("posts", posts);
        return userServiceImpl.takeUserData(userDetails);
    }
}