package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UsersRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    public UsersRoles(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
