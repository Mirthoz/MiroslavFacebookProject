package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.FriendRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestsRepository extends JpaRepository<FriendRequests, Long> {
}
