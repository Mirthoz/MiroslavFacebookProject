package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
}