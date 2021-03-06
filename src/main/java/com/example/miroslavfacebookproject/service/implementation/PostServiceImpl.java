package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.entity.Image;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.entity.UserFriend;
import com.example.miroslavfacebookproject.enums.PostStatus;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.repository.UserFriendsRepository;
import com.example.miroslavfacebookproject.service.contract.PostService;
import com.example.miroslavfacebookproject.service.contract.UploadImageService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    public final PostRepository postRepository;
    public final UploadImageService uploadImageService;
    public final UserFriendsRepository userFriendsRepository;

    public PostServiceImpl(PostRepository postRepository,
                           UploadImageService uploadImageService,
                           UserFriendsRepository userFriendsRepository) {
        this.postRepository = postRepository;
        this.uploadImageService = uploadImageService;
        this.userFriendsRepository = userFriendsRepository;
    }

    @Override
    public void savePost(PostDTO postDTO, User user) throws IOException {
        Date date = new Date();
        Post post = new Post();

        if (!postDTO.getPostImage().isEmpty()) {
            uploadImageService.uploadImage(postDTO.getPostImage());
            Image image = new Image(uploadImageService.takeImageURL());
            post.setPostImageURL(image.getImageUrl());
        }
        post.setText(postDTO.getPostText());
        post.setPoster(user);
        post.setDate(date);
        post.setStatus(PostStatus.ACTIVE.name());
        postRepository.save(post);
    }

    @Override
    public void reportPost(Long postId) {
        Post post = postRepository.findFirstById(postId);
        post.setStatus(PostStatus.REPORTED.name());
        postRepository.save(post);
    }

    @Override
    public void blockPost(Long postId) {
        Post post = postRepository.findFirstById(postId);
        post.setStatus(PostStatus.BLOCKED.name());
        postRepository.save(post);
    }

    @Override
    public List<User> takeUserFriends(Long currentUserId) {
        return userFriendsRepository.findAllByUserIdId(currentUserId).stream().map(UserFriend::getFriendId).collect(Collectors.toList());
    }
}