package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.entity.FriendRequest;
import com.example.miroslavfacebookproject.repository.FriendRequestRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.FriendService;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    public FriendServiceImpl(FriendRequestRepository friendRequestRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addFriend(Long friendId, Long user) {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setReceiver(userRepository.findUserById(friendId));
        friendRequest.setRequester(userRepository.findUserById(user));
        friendRequestRepository.save(friendRequest);
    }
}