package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.LikeService;
import com.example.miroslavfacebookproject.service.contract.PrivacyService;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublicProfileController extends BaseController {

    private final UserRepository userRepository;
    private final LikeService likeService;
    private final ProfileService profileService;
    private final PrivacyService privacyService;

    public PublicProfileController(UserRepository userRepository,
                                   LikeService likeService,
                                   ProfileService profileService,
                                   PrivacyService privacyService) {
        this.userRepository = userRepository;
        this.likeService = likeService;
        this.profileService = profileService;
        this.privacyService = privacyService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/public_profile")
    public ModelAndView showPublicProfile(@AuthenticationPrincipal User currentUser, @ModelAttribute("searchProfile") SearchUserDTO searchUserDTO, Model model) {
        likeService.checkingLikes(currentUser);
        LikesController.publicProfileId = searchUserDTO.getUserId();
        CommentsController.publicProfileId = searchUserDTO.getUserId();
        User user = userRepository.findUserById(searchUserDTO.getUserId());
        model.addAttribute("privacyDTO", privacyService.createPrivacyDTO(user));
        return profileService.sendProfileData(user, model);
    }
}
