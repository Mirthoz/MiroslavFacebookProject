package com.example.miroslavfacebookproject.service.implementation;
import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.dto.UserDTO;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.dto.RegisterDTO;
import com.example.miroslavfacebookproject.entity.Role;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PostRepository postRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService, BCryptPasswordEncoder passwordEncoder, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.postRepository = postRepository;
    }

    @Override
    public User register(RegisterDTO registerDTO) {
        if (registerDTO.getPasswordRepeat() == null || !registerDTO.getPassword().equals(registerDTO.getPasswordRepeat()))
            throw new IllegalArgumentException("Password do not match");

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setAge(registerDTO.getAge());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getUserRole());
        user.setRoles(roles);

        userRepository.save(user);
        return user;
    }

    @Override
    public String login(String email, String password) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found; with email: " + email));
        return user;
    }

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found; with username: " + username));
        return user;
    }

    public ModelAndView getUserData(UserDetails userDetails) {
        com.example.miroslavfacebookproject.entity.User user = getUserByUsername(userDetails.getUsername());
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setAge(user.getAge());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userDTO", userDTO);
        return modelAndView;
    }

    public void changeData(UserDTO userDTO, UserDetails userDetails) {
        com.example.miroslavfacebookproject.entity.User user = getUserByUsername(userDetails.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setAge(userDTO.getAge());
        user.setUsername(userDTO.getUsername());
        userRepository.save(user);
    }

    public void resetUserPassword(String email, String password, String passwordRepeat) {
        if (password.equals(passwordRepeat) && userRepository.findByEmail(email) != null) {
            User user = userRepository.findByEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            System.out.println("Password is reset!!!");
        }
    }

    public void savePost(PostDTO postDTO, User user){
        Post post = new Post();
        post.setText(postDTO.getPostText());
        post.setPoster(user);
        postRepository.save(post);
    }
}
