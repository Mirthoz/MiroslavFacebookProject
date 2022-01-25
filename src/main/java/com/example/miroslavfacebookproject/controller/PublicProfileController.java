package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.PrivacyDTO;
import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.LikeService;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublicProfileController extends BaseController{

    private final UserRepository userRepository;
    private final LikeService likeService;
    private final ProfileService profileService;

    public PublicProfileController(UserRepository userRepository,
                                   LikeService likeService,
                                   ProfileService profileService) {
        this.userRepository = userRepository;
        this.likeService = likeService;
        this.profileService = profileService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/public_profile")
    public ModelAndView searchUserProfile(@ModelAttribute("searchProfile") SearchUserDTO searchUserDTO, Model model, PrivacyDTO privacyDTO) {
        LikesController.publicProfileId = searchUserDTO.getUserId();
        CommentsController.publicProfileId = searchUserDTO.getUserId();
        User user = userRepository.findUserById(searchUserDTO.getUserId());
        likeService.checkingLikes(user);
        privacyDTO.setEmailPrivacy(privacyConvertor(user.getEmailPrivacy()));
        privacyDTO.setAgePrivacy(privacyConvertor(user.getAgePrivacy()));
        privacyDTO.setPostsAndImagesPrivacy(privacyConvertor(user.getPostsAndImagesPrivacy()));
        model.addAttribute("privacyDTO", privacyDTO);
        return profileService.sendProfileData(user, model);
    }

    private boolean privacyConvertor(String privacy){
        return privacy.equals("FOR_ALL");}
}
