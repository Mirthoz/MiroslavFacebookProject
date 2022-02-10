package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.dto.LikeDTO;
import com.example.miroslavfacebookproject.entity.Like;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.LikeRepository;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.service.contract.BlockUserService;
import com.example.miroslavfacebookproject.service.contract.LikeService;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final BlockUserService blockUserService;

    public LikeServiceImpl(LikeRepository likeRepository,
                           PostRepository postRepository,
                           BlockUserService blockUserService) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.blockUserService = blockUserService;
    }

    @Override
    public void likePost(LikeDTO likeDTO, User currentUser) {
        Post post = postRepository.findFirstById(likeDTO.getPostId());
        if (!blockUserService.checkIsCurrentUserIdOnTheBlockedList(currentUser.getId(), post.getPoster().getId())) {

            if (!likeRepository.existsByPostAndUser(post, currentUser)) {
                Like like = new Like();
                like.setPost(post);
                like.setUser(currentUser);
                likeRepository.save(like);
                post.getLikes().add(like);
                postRepository.save(post);
            } else {
                Like like = likeRepository.findFirstByPostAndUser(post, currentUser);
                post.getLikes().remove(like);
                likeRepository.delete(like);
            }
        }
    }

    @Override
    public void checkingLikes(User currentUser) {
        postRepository.findAll().forEach(x -> markLike(x.getId(), currentUser));
    }

    protected void markLike(Long id, User currentUser) {
        Post post = postRepository.findFirstById(id);
        Like like = likeRepository.findFirstByPostAndUser(post, currentUser);
        if (like != null) {
            postRepository.findFirstById(id).setMeLiked(0);
        } else
            postRepository.findFirstById(id).setMeLiked(1);
    }
}