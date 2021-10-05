package com.example.miroslavfacebookproject.repository;
import com.example.miroslavfacebookproject.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersBlockedUsersRepository extends JpaRepository <Users, Long>{
}
