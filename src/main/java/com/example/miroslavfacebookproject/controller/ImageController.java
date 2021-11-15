package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.ImageUploadDTO;
import com.example.miroslavfacebookproject.service.implementation.UploadImageServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImageController extends BaseController{
    private final UploadImageServiceImpl fileService;

    public ImageController(UploadImageServiceImpl fileService) {
        this.fileService = fileService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/pic")
    public ModelAndView upload(@ModelAttribute("file") ImageUploadDTO multipartFile) {
        System.out.println(multipartFile.getImage());
        fileService.upload(multipartFile.getImage());
        return redirect("profile");
    }
}
