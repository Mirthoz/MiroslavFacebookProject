package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.FriendDTO;
import com.example.miroslavfacebookproject.entity.FriendRequest;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.FriendRequestRepository;
import com.example.miroslavfacebookproject.service.contract.FriendService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Set;

@Controller
public class FriendsController extends BaseController{

    private final FriendService friendService;
    private final FriendRequestRepository friendRequestRepository;

    public FriendsController(FriendService friendService, FriendRequestRepository friendRequestRepository) {
        this.friendService = friendService;
        this.friendRequestRepository = friendRequestRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("friends")
    public ModelAndView friends(@AuthenticationPrincipal User user){
        Set<FriendRequest> friendRequests = friendRequestRepository.findAllByReceiverId(user.getId());
        return send("friends", "requests", friendRequests);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("addFriend")
    public ModelAndView addNewFriendRequest(@AuthenticationPrincipal User user, @ModelAttribute FriendDTO friendDTO){
        friendService.addFriendRequest(friendDTO.getFriendId(), user.getId());
        return redirect("profile");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("addAsFriend")
    public ModelAndView addAsFriend(@AuthenticationPrincipal User user, @ModelAttribute FriendDTO friendDTO){
        friendService.addFriend(friendDTO.getFriendId(), user.getId());
        return redirect("friends");
    }
}
