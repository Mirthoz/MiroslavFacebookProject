package com.example.miroslavfacebookproject.service.contract;

import org.springframework.stereotype.Service;

@Service
public interface FriendService {
    void addFriendRequest(Long friendId, Long user);
    void addFriend(Long friendId, Long user);
}
