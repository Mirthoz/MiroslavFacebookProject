package com.example.miroslavfacebookproject.repository;

import com.example.miroslavfacebookproject.entity.AlbumImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumImageRepository extends JpaRepository <AlbumImage, Long> {
}
