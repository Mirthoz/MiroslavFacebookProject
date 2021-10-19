package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;

    public ProfileServiceImpl(UserRepository userRepository, UserServiceImpl userServiceImpl) {
        this.userRepository = userRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void deleteProfile(UserDetails userName) {
        User user = userServiceImpl.getUserByUsername(userName.getUsername());
        userRepository.delete(user);
    }
}
