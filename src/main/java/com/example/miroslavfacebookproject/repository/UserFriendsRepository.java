package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.FriendRequest;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.entity.UserFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFriendsRepository extends JpaRepository<UserFriends, Long> {

//    List<UserFriends> findAllByUser(User user);
}
