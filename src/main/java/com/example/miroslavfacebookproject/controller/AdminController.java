package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.PostDTO;
import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.service.contract.AdminService;
import com.example.miroslavfacebookproject.service.contract.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController extends BaseController {
    private final AdminService adminService;
    private final PostService postService;

    public AdminController(AdminService adminService, PostService postService) {
        this.adminService = adminService;
        this.postService = postService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/only_for_admins")
    public ModelAndView onlyForAdminsPage(ModelAndView modelAndView){
        modelAndView.addObject("posts", adminService.showReportedPosts());
        modelAndView.setViewName("only_for_admins");
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/make_admin")
    public ModelAndView makeAdmin(@ModelAttribute("make_admin") SearchUserDTO searchUserDTO) {
        adminService.makeAdmin(searchUserDTO.getUserId());
        return redirect("profile");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/make_user")
    public ModelAndView makeUser(@ModelAttribute("make_user") SearchUserDTO searchUserDTO) {
        adminService.makeUser(searchUserDTO.getUserId());
        return redirect("profile");
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("block_post")
    public ModelAndView blockPost(@ModelAttribute("block_post") PostDTO postDTO)  {
        postService.blockPost(postDTO.getPostId());
        return redirect("only_for_admins");
    }
}
