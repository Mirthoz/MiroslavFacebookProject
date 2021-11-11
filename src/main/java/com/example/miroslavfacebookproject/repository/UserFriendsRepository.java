package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.UserFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface UserFriendsRepository extends JpaRepository<UserFriends, Long> {
    Set<UserFriends> findAllByUserIdId(Long userId);
}
