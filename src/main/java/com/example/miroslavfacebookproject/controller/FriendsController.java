package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.FriendDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.FriendService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FriendsController extends BaseController{

    private final FriendService friendService;

    public FriendsController(FriendService friendService) {
        this.friendService = friendService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("friends")
    public ModelAndView friends(@AuthenticationPrincipal User user){
        return send("friends");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("addFriend")
    public ModelAndView addNewFriend(@AuthenticationPrincipal User user, @ModelAttribute FriendDTO friendDTO){
        friendService.addFriend(friendDTO.getFriendId(), user.getId());
        System.out.println("userId: " + user.getId());
        System.out.println("friendId: " + friendDTO.getFriendId());
        System.out.println("Saved!!!");
        return redirect("profile");
    }
}
