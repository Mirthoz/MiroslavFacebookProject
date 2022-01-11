package com.example.miroslavfacebookproject.service.contract;

import com.example.miroslavfacebookproject.dto.SearchUserDTO;
import com.example.miroslavfacebookproject.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Service
public interface ProfileService {

    void deleteProfile(UserDetails userDetails);

    ModelAndView searchUserByName(User user, SearchUserDTO searchUserDTO, Model model);

    ModelAndView sendProfileData(User currentUser, Model model);

    ModelAndView takeUserData(User currentUser);
}
