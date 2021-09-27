package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "users_requests")
public class UsersRequests {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long requesterId;

    @Column(name = "friend_request_id")
    private Long friendRequestId;

    public UsersRequests(){}

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getFriendRequestId() {
        return friendRequestId;
    }

    public void setFriendRequestId(Long friendRequestId) {
        this.friendRequestId = friendRequestId;
    }
}
