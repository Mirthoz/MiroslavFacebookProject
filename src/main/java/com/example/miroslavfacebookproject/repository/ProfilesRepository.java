package com.example.miroslavfacebookproject.repository;
import com.example.miroslavfacebookproject.entity.FriendRequests;
import com.example.miroslavfacebookproject.entity.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRepository extends JpaRepository <Profiles, Long>{
}
