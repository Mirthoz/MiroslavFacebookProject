package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.controller.BaseController;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.MetadataConfig;
import com.google.cloud.storage.*;
import io.grpc.Metadata;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadImageServiceImpl extends BaseController {

    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/facebookproject-3ac7a.appspot.com/o/%s?alt=image/jpeg";
    private static String TEMP_URL = "http://localhost:8080/profile";
    private static final String KEY = "C:\\Users\\PC\\IdeaProjects\\MiroslavFacebookProject\\src\\main\\resources\\serviceAccountKey.json";

    public String downloadUrl;
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
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IllegalStateException, IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        image = fileName;
        return tempFile;
    }

    public Object upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return send(TEMP_URL);                                                        // Customized response
        } catch (Exception e) {
            e.printStackTrace();
            return send(TEMP_URL);
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }



    public void takeMetaData(String fileName) throws IOException, InterruptedException {

        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(KEY));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("facebookproject-3ac7a.appspot.com", fileName));

        System.out.println(blob.toString());
    }
}
