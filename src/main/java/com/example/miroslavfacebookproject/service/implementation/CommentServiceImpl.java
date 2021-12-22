package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.controller.BaseController;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseController implements CommentService {

    @Override
    public void addComment(String text, User currentUser) {
        System.out.println(text + " " + currentUser.getUsername() + " " + currentUser.getSurname());
    }
}
