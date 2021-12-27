package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.CommentDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.CommentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentsController extends BaseController {

    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add_comment")
    public ModelAndView addComment(@AuthenticationPrincipal User currentUser, CommentDTO commentDTO){
        commentService.addComment(commentDTO, currentUser);
        return redirect("profile");
    }
}
