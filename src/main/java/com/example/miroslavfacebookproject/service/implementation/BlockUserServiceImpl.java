package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.BlockUserService;
import org.springframework.stereotype.Service;

@Service
public class BlockUserServiceImpl implements BlockUserService {

    private final UserRepository userRepository;

    public BlockUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void blockUser(Long currentUserId, Long blockedUserId) {
        User currentUser = userRepository.findUserById(currentUserId);
        User blockedUser = userRepository.findUserById(blockedUserId);
        currentUser.getBlockedUsers().add(blockedUser);
        userRepository.save(currentUser);
    }

    @Override
    public boolean checkIsCurrentUserIdOnTheBlockedList(Long currentUserId, Long targetUserId) {
        User checkedUser = userRepository.findUserById(targetUserId);
        User currentUser = userRepository.findUserById(currentUserId);
        return checkedUser.getBlockedUsers().contains(currentUser);
    }
}
