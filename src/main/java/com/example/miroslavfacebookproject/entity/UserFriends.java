package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_friends")
public class UserFriends {

    @Id
    @Column
    private Long friendId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
