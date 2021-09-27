package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "friend_requests")
public class FriendRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "requested_id")
    private Long requestedId;

    @Column(name = "status")
    private Enum status;

    public FriendRequests(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestedId() {
        return requestedId;
    }

    public void setRequestedId(Long requestedId) {
        this.requestedId = requestedId;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }
}
