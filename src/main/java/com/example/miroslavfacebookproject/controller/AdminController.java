package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.service.contract.AdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController extends BaseController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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
}
