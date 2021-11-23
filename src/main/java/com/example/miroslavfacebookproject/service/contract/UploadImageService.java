package com.example.miroslavfacebookproject.service.contract;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public interface UploadImageService {
    Object uploadImage(MultipartFile image) throws IOException;

    String takeImageURL();
}
