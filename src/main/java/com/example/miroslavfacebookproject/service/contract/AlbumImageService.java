package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.ImageUploadDTO;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public interface AlbumImageService {

    void addAlbumImage(User currentUser, ImageUploadDTO imageUploadDTO);

    ModelAndView takeAlbumImages(User currentUser);
}