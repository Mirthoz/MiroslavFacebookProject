package com.example.miroslavfacebookproject.controller;

import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.service.contract.BlockUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BlockUserController extends BaseController{
    private final BlockUserService blockUserService;

    public BlockUserController(BlockUserService blockUserService) {
        this.blockUserService = blockUserService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/block")
    public ModelAndView blockUser(@AuthenticationPrincipal User currentUser, @ModelAttribute("block") SearchUserDTO searchUserDTO) {
        blockUserService.blockUser(currentUser.getId(), searchUserDTO.getUserId());
        return redirect("profile");
    }
}
