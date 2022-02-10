package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.controller.BaseController;
import com.example.miroslavfacebookproject.dto.CommentDTO;
import com.example.miroslavfacebookproject.entity.Comment;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.CommentRepository;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.service.contract.BlockUserService;
import com.example.miroslavfacebookproject.service.contract.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseController implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final BlockUserService blockUserService;

    public CommentServiceImpl(PostRepository postRepository,
                              CommentRepository commentRepository,
                              BlockUserService blockUserService) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.blockUserService = blockUserService;
    }

    @Override
    public void addComment(CommentDTO commentDTO, User currentUser) {
        Post currentPost = postRepository.findFirstById(commentDTO.getCurrentPostId());
        if (!blockUserService.checkIsCurrentUserIdOnTheBlockedList(currentUser.getId(), currentPost.getPoster().getId())) {
            String commentatorNameAndSurname = currentUser.getUsername() + " " + currentUser.getSurname();
            Comment freshComment = new Comment(commentDTO.getCommentText(), commentatorNameAndSurname);
            commentRepository.save(freshComment);
            currentPost.getComments().add(freshComment);
            postRepository.save(currentPost);
        }
    }
}
