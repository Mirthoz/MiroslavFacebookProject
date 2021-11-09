package com.example.miroslavfacebookproject.repository;
import com.example.miroslavfacebookproject.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
Set<FriendRequest> findAllByReceiverId(Long userId);
}
