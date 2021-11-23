package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.service.contract.PostService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostServiceImpl implements PostService {

    public final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void savePost(PostDTO postDTO, User user) {
            Date date = new Date();
            Post post = new Post();
            post.setText(postDTO.getPostText());
            post.setPoster(user);
            post.setDate(date);
            postRepository.save(post);
    }
}
