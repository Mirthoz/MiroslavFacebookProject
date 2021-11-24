package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.ImageUploadDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.AlbumImageService;
import com.example.miroslavfacebookproject.service.contract.UploadImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Controller
public class AlbumController extends BaseController {

    private final AlbumImageService albumImageService;
    private final UploadImageService uploadImageService;

    public AlbumController(AlbumImageService albumImageService, UploadImageService uploadImageService) {
        this.albumImageService = albumImageService;
        this.uploadImageService = uploadImageService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/photos")
    public ModelAndView photos(@AuthenticationPrincipal User currentUser) {
        return albumImageService.takeAlbumImages(currentUser);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/add_album_image")
    public ModelAndView addAlbumImage(@AuthenticationPrincipal User currentUser, @ModelAttribute("file") ImageUploadDTO imageUploadDTO) throws IOException {
        uploadImageService.uploadImage(imageUploadDTO.getImage());
        albumImageService.addAlbumImage(currentUser, imageUploadDTO);
        return redirect("photos");
    }
}
