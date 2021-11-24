package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);

    Optional<User> findFirstByUsername(String username);

    User findByEmail(String email);

    User findUserById(Long id);

    List<User> findByUsernameContainingIgnoreCase(@Param("username") String username);
}
