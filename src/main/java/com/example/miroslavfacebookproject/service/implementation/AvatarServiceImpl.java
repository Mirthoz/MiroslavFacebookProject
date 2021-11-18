package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.entity.Avatar;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.AvatarRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.AvatarService;
import org.springframework.stereotype.Service;

@Service
public class AvatarServiceImpl implements AvatarService {
    private final UserRepository userRepository;
    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(UserRepository userRepository, AvatarRepository avatarRepository) {
        this.userRepository = userRepository;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public void addAvatar(Long currentUser, String avatarURL) {
        User user = userRepository.findUserById(currentUser);
        Avatar avatar = avatarRepository.findFirstById(user.getAvatar().getId());
        avatar.setAvatarURL(avatarURL);
        user.setAvatar(avatar);
        userRepository.save(user);
    }
}
