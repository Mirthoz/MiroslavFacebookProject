package com.example.miroslavfacebookproject.service.implementation;

import com.example.miroslavfacebookproject.dto.PrivacyDTO;
import com.example.miroslavfacebookproject.entity.User;
import com.example.miroslavfacebookproject.repository.UserRepository;
import com.example.miroslavfacebookproject.service.contract.LikeService;
import com.example.miroslavfacebookproject.service.contract.PrivacyService;
import com.example.miroslavfacebookproject.service.contract.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class PrivacyServiceImpl implements PrivacyService {

    private final UserRepository userRepository;
    private final LikeService likeService;
    private final ProfileService profileService;

    public PrivacyServiceImpl(UserRepository userRepository, LikeService likeService, ProfileService profileService) {
        this.userRepository = userRepository;
        this.likeService = likeService;
        this.profileService = profileService;
    }

    @Override
    public PrivacyDTO createPrivacyDTO(User user) {
        PrivacyDTO privacyDTO = new PrivacyDTO();
        privacyDTO.setEmailPrivacy(privacyConvertor(user.getEmailPrivacy()));
        privacyDTO.setAgePrivacy(privacyConvertor(user.getAgePrivacy()));
        privacyDTO.setPostsAndImagesPrivacy(privacyConvertor(user.getPostsAndImagesPrivacy()));
        return privacyDTO;
    }

    private boolean privacyConvertor(String privacy){
        return privacy.equals("FOR_ALL");}
}
