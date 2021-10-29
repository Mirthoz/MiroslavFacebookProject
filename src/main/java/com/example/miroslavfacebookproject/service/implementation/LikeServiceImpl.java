package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.dto.LikeDTO;
import com.example.miroslavfacebookproject.entity.Like;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.LikeRepository;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.service.contract.LikeService;

public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public LikeServiceImpl(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void likePost(LikeDTO likeDTO, User currentUser) {
        Post post = postRepository.findFirstById(likeDTO.getPostId());
        if (!likeRepository.existsByPost(post)){
            Like like = new Like();
            like.setPost(post);
            like.setUser(currentUser);
            likeRepository.save(like);
            post.getLikes().add(like);
            postRepository.save(post);
        }else {
            Like like = likeRepository.findFirstByPostAndUser(post, currentUser);
            post.getLikes().remove(like);
            likeRepository.delete(like);
        }
    }
}