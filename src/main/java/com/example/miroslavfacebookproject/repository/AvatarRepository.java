package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findFirstById(Long id);
}
