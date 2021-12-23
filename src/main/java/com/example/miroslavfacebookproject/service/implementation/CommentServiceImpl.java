package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.controller.BaseController;
import com.example.miroslavfacebookproject.dto.CommentDTO;
import com.example.miroslavfacebookproject.entity.Comment;
import com.example.miroslavfacebookproject.entity.Post;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.CommentRepository;
import com.example.miroslavfacebookproject.repository.PostRepository;
import com.example.miroslavfacebookproject.service.contract.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseController implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void addComment(CommentDTO commentDTO, User currentUser) {
        String commentatorNameAndSurname = currentUser.getUsername() + " " + currentUser.getSurname();
        Post currentPost = postRepository.findFirstById(commentDTO.getCurrentPostId());
        Comment freshComment = new Comment(commentDTO.getCommentText(), commentatorNameAndSurname);
        commentRepository.save(freshComment);
        currentPost.getComments().add(freshComment);
        postRepository.save(currentPost);

        System.out.println(currentPost.getComments().size());
        System.out.println(commentDTO.getCommentText() + " " + currentUser.getUsername() + " " + currentUser.getSurname() + "Post Id: " + commentDTO.getCurrentPostId());
    }
}
