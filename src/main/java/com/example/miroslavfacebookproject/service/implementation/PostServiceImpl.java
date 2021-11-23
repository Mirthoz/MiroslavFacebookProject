package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.entity.Image;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.service.contract.PostService;
import com.example.miroslavfacebookproject.service.contract.UploadImageService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

    public final PostRepository postRepository;
    public final UploadImageService uploadImageService;

    public PostServiceImpl(PostRepository postRepository, UploadImageService uploadImageService) {
        this.postRepository = postRepository;
        this.uploadImageService = uploadImageService;
    }

    @Override
    public void savePost(PostDTO postDTO, User user) throws IOException {
            Date date = new Date();
            Post post = new Post();

        if(!postDTO.getPostImage().isEmpty()){
            uploadImageService.uploadImage(postDTO.getPostImage());
            Image image = new Image(uploadImageService.takeImageURL());
            post.setPostImageURL(image.getImageUrl());
        }
            post.setText(postDTO.getPostText());
            post.setPoster(user);
            post.setDate(date);
            postRepository.save(post);
    }
}