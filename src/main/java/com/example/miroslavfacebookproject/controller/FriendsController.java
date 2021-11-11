package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.FriendDTO;
import com.example.miroslavfacebookproject.entity.FriendRequest;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.entity.UserFriend;
import com.example.miroslavfacebookproject.repository.FriendRequestRepository;
import com.example.miroslavfacebookproject.repository.UserFriendsRepository;
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
    private final UserFriendsRepository userFriendsRepository;

    public FriendsController(FriendService friendService, FriendRequestRepository friendRequestRepository, UserFriendsRepository userFriendsRepository) {
        this.friendService = friendService;
        this.friendRequestRepository = friendRequestRepository;
        this.userFriendsRepository = userFriendsRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("friends")
    public ModelAndView friends(@AuthenticationPrincipal User user, ModelAndView modelAndView){
        Set<FriendRequest> friendRequests = friendRequestRepository.findAllByReceiverId(user.getId());
        Set<UserFriend> userFriends = userFriendsRepository.findAllByUserIdId(user.getId());
        modelAndView.addObject("userFriends", userFriends);
        modelAndView.addObject("friendRequests", friendRequests);
        modelAndView.setViewName("friends");
        return modelAndView;
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("deleteFriend")
    public ModelAndView deleteFriend(@ModelAttribute FriendDTO friendDTO){
        friendService.deleteFriend(friendDTO.getFriendId());
        return redirect("friends");
    }
}
