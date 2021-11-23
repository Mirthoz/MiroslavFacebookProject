package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.entity.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserFriendsRepository extends JpaRepository<UserFriend, Long> {
    Set<UserFriend> findAllByUserIdId(Long userId);

    Set<UserFriend> findAllByFriendIdId(Long userId);

    UserFriend findFirstUserFriendByFriendId(User friendId);
}
