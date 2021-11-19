package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.ImageUploadDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.AvatarService;
import com.example.miroslavfacebookproject.service.implementation.UploadImageServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AvatarController extends BaseController{
    private final UploadImageServiceImpl fileService;
    private final AvatarService avatarService;

    public AvatarController(UploadImageServiceImpl fileService, AvatarService avatarService) {
        this.fileService = fileService;
        this.avatarService = avatarService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/pic")
    public ModelAndView upload(@AuthenticationPrincipal User user, @ModelAttribute("file") ImageUploadDTO imageUploadDTO) {
        fileService.uploadImage(imageUploadDTO.getImage());
        avatarService.addAvatar(user.getId(), fileService.imageUrl);
        return redirect("profile");
    }
}