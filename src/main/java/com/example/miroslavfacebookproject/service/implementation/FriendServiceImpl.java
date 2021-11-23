package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.entity.FriendRequest;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.entity.UserFriend;
import com.example.miroslavfacebookproject.repository.FriendRequestRepository;
import com.example.miroslavfacebookproject.repository.UserFriendsRepository;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.FriendService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import java.util.Set;

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
    public void addFriendRequest(Long friendId, Long userId) {
        FriendRequest checkFriendRequest = friendRequestRepository.findFirstByReceiverIdAndRequesterId(friendId, userId);
        if (checkFriendRequest == null) {
            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setReceiver(userRepository.findUserById(friendId));
            friendRequest.setRequester(userRepository.findUserById(userId));
            friendRequestRepository.save(friendRequest);
        }
    }

    @Override
    public void addFriend(Long friendId, Long user) {
        UserFriend userFriend = new UserFriend();
        userFriend.setUserId(userRepository.findUserById(user));
        userFriend.setFriendId(userRepository.findUserById(friendId));
        userFriendsRepository.save(userFriend);
        deleteFriendRequest(friendId, user);
    }

    protected void deleteFriendRequest(Long friendId, Long userId) {
        FriendRequest friendRequest = friendRequestRepository.findFirstByReceiverIdAndRequesterId(userId, friendId);
        friendRequestRepository.delete(friendRequest);
    }

    @Override
    public void deleteFriend(Long friendId) {
        UserFriend userFriend = userFriendsRepository.findFirstUserFriendByFriendId(userRepository.findUserById(friendId));
        userFriendsRepository.delete(userFriend);
    }

    @Override
    public ModelAndView showFriends(User currentUser, ModelAndView modelAndView) {
        Set<FriendRequest> friendRequests = friendRequestRepository.findAllByReceiverId(currentUser.getId());
        Set<UserFriend> userFriends = userFriendsRepository.findAllByUserIdId(currentUser.getId());
        if (userFriends.isEmpty()) {
            userFriends = userFriendsRepository.findAllByFriendIdId(currentUser.getId());
        }
        modelAndView.addObject("userFriends", userFriends);
        modelAndView.addObject("friendRequests", friendRequests);
        modelAndView.setViewName("friends");
        return modelAndView;
    }
}