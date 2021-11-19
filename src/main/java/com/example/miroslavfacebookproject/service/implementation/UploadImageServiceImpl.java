package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.controller.BaseController;
import com.example.miroslavfacebookproject.service.contract.UploadImageService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadImageServiceImpl extends BaseController implements UploadImageService {

    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/facebookproject-3ac7a.appspot.com/o/%s?alt=image/jpeg";
    private static String TEMP_URL = "http://localhost:8080/profile";
    private static final String KEY = "C:\\Users\\PC\\IdeaProjects\\MiroslavFacebookProject\\src\\main\\resources\\serviceAccountKey.json";

    public String imageUrl;
    public String image;

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("facebookproject-3ac7a.appspot.com", fileName);
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType("image/jpeg")
                .setMetadata(map)
                .build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(KEY));
        Storage storage = StorageOptions
                .newBuilder()
                .setCredentials(credentials)
                .build()
                .getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/facebookproject-3ac7a.appspot.com/o/"
                + fileName + "?alt=media&token=" + fileName;
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IllegalStateException, IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        }
        image = fileName;
        return tempFile;
    }
    @Override
    public Object uploadImage(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = this.convertToFile(multipartFile, fileName);
            TEMP_URL = this.uploadFile(file, fileName);
            file.delete();
            return send(TEMP_URL);
        } catch (Exception e) {
            e.printStackTrace();
            return send(TEMP_URL);
        }
    }

    @Override
    public String takeImageURL() {
        return imageUrl;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
