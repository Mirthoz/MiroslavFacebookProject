package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.entity.FriendRequest;
import com.example.miroslavfacebookproject.entity.UserFriends;
import com.example.miroslavfacebookproject.repository.FriendRequestRepository;
import com.example.miroslavfacebookproject.repository.UserFriendsRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.FriendService;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserFriendsRepository userFriendsRepository;
    private final UserRepository userRepository;

    public FriendServiceImpl(FriendRequestRepository friendRequestRepository, UserFriendsRepository userFriendsRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userFriendsRepository = userFriendsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addFriendRequest(Long friendId, Long user) {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setReceiver(userRepository.findUserById(friendId));
        friendRequest.setRequester(userRepository.findUserById(user));
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public void addFriend(Long friendId, Long user) {
        UserFriends userFriends = new UserFriends();
        userFriends.setUserId(userRepository.findUserById(user));
        userFriends.setFriendId(userRepository.findUserById(friendId));
        userFriendsRepository.save(userFriends);
        deleteFriendRequest(user, friendId);
    }

    protected void deleteFriendRequest(Long friendId, Long user) {
        friendRequestRepository.delete(friendRequestRepository.findFirstByReceiverIdAndRequesterId(user, friendId));
    }

}