package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.ImageUploadDTO;
import com.example.miroslavfacebookproject.service.implementation.UploadImageServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ImageController extends BaseController{
    private final UploadImageServiceImpl fileService;

    public ImageController(UploadImageServiceImpl fileService) {
        this.fileService = fileService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/pic")
    public ModelAndView upload(@ModelAttribute("file") ImageUploadDTO multipartFile) throws IOException, InterruptedException {
        System.out.println(multipartFile.getImage());
        fileService.upload(multipartFile.getImage());
        System.out.println(fileService.image);
        redirect("https://firebasestorage.clients6.google.com/v0/b/facebookproject-3ac7a.appspot.com/o/" + fileService.image + "?create_token=true");
        return redirect("profile");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/take")
    public ModelAndView takeData() throws IOException, InterruptedException {
        System.out.println(fileService.image);
        fileService.takeMetaData(fileService.image);
        return redirect("profile");
    }
}