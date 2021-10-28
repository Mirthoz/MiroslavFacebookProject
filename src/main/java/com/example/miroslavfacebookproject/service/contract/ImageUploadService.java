package com.example.miroslavfacebookproject.service.contract;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploadService {
    void uploadImage(MultipartFile image) throws IOException;
}
