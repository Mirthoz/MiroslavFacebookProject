package com.example.miroslavfacebookproject.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @OneToMany(targetEntity = FriendRequest.class, fetch = FetchType.EAGER, mappedBy = "requester", orphanRemoval = true)
    private Set<FriendRequest> friendRequests;

    @OneToMany(targetEntity = UserFriend.class, fetch = FetchType.EAGER, mappedBy = "friendId", orphanRemoval = true)
    private Set<UserFriend> userFriends;

    @OneToOne
    @JoinColumn(name = "avatar", referencedColumnName = "id")
    private Avatar avatar;

    @OneToMany(fetch = FetchType.EAGER)
    private List<AlbumImage> albumImages = new ArrayList<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<FriendRequest> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(Set<FriendRequest> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public Set<UserFriend> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(Set<UserFriend> userFriends) {
        this.userFriends = userFriends;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public List<AlbumImage> getAlbumImages() {
        return albumImages;
    }

    public void setAlbumImages(List<AlbumImage> albumImages) {
        this.albumImages = albumImages;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
