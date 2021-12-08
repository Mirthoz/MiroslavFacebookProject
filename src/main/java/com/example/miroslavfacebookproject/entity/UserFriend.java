package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_friends")
public class UserFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private User friendId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public UserFriend() {
    }

    public UserFriend(User friendId, User userId) {
        this.friendId = friendId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFriendId() {
        return friendId;
    }

    public void setFriendId(User friendId) {
        this.friendId = friendId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
