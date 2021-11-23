package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.ImageUploadDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.AvatarService;
import com.example.miroslavfacebookproject.service.contract.UploadImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class AvatarController extends BaseController {
    private final UploadImageService uploadImageService;
    private final AvatarService avatarService;

    public AvatarController(UploadImageService uploadImageService, AvatarService avatarService) {
        this.uploadImageService = uploadImageService;
        this.avatarService = avatarService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/pic")
    public ModelAndView upload(@AuthenticationPrincipal User user, @ModelAttribute("file") ImageUploadDTO imageUploadDTO) throws IOException {
        uploadImageService.uploadImage(imageUploadDTO.getImage());
        avatarService.addAvatar(user.getId(), uploadImageService.takeImageURL());
        return redirect("profile");
    }
}