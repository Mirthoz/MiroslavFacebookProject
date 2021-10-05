package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_friends")
public class UsersFriends {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "friend_user_id")
    private Long friend_user_id;

    public UsersFriends(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriend_user_id() {
        return friend_user_id;
    }

    public void setFriend_user_id(Long friend_user_id) {
        this.friend_user_id = friend_user_id;
    }
}
