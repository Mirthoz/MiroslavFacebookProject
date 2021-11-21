package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public interface FriendService {
    void addFriendRequest(Long friendId, Long user);
    void addFriend(Long friendId, Long user);
    void deleteFriend(Long friendId);
    ModelAndView showFriends(User currentUser, ModelAndView modelAndView);
}
