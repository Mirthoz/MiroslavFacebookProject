package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_blocked_users")
public class UsersBlockedUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "blocked_user_id")
    private Long blockedUserId;

    public UsersBlockedUsers(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBlockedUserId() {
        return blockedUserId;
    }

    public void setBlockedUserId(Long blockedUserId) {
        this.blockedUserId = blockedUserId;
    }
}
