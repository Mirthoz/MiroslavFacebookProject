package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.service.contract.ImageUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadServiceImpl implements ImageUploadService {

    @Override
    public void uploadImage(MultipartFile image) throws IOException {
        Path filepath = Paths.get("", image.getOriginalFilename());
        image.transferTo(filepath);
    }
}
