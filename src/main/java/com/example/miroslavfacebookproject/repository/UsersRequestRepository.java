package com.example.miroslavfacebookproject.repository;
import com.example.miroslavfacebookproject.entity.UsersRequests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRequestRepository extends JpaRepository <UsersRequests, Long>{
}
