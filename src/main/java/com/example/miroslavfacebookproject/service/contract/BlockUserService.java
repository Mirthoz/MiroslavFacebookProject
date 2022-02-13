package com.example.miroslavfacebookproject.service.contract;

import org.springframework.stereotype.Service;

@Service
public interface BlockUserService {
    void blockUser(Long currentUserId, Long blockedUserId);

    boolean checkIsCurrentUserIdIsBlocked(Long currentUserId, Long targetUerId);
}
