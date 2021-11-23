package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchUserController extends BaseController{
    private final ProfileService profileService;

    public SearchUserController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/search_user")
    public ModelAndView searchUserByName(@AuthenticationPrincipal User user, @ModelAttribute("username") SearchUserDTO searchUserDTO, Model model){
        return profileService.searchUserByName(user, searchUserDTO, model);}
}
