package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.Role;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public AdminServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void makeAdmin(Long targetUserId) {
        Role role = new Role(2L, "ROLE_ADMIN");
        User targetUser = userRepository.findUserById(targetUserId);
        Set<Role> roles = targetUser.getRoles();
        roles.clear();
        roles.add(role);
        userRepository.save(targetUser);
    }

    @Override
    public void makeUser(Long targetUserId) {
        Role role = new Role(1L, "ROLE_USER");
        User targetUser = userRepository.findUserById(targetUserId);
        Set<Role> roles = targetUser.getRoles();
        roles.clear();
        roles.add(role);
        userRepository.save(targetUser);
    }

    @Override
    public List<Post> showReportedPosts() {
        return postRepository.findAll().stream().filter(post -> post.getStatus().equals("REPORTED")).collect(Collectors.toList());
    }
}