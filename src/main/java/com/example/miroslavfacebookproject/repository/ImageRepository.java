package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getFirstByImageUrl(String url);
}
