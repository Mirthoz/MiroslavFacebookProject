package com.example.miroslavfacebookproject.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "status")
    private Integer status; //1- only for user, 2- for friends and user, 3- for all

    @Column(name = "date")
    private Date date;

    @Column(name = "liked")
    private int meLiked;

    @Column(name = "post_image_url")
    private String postImageURL;

    public Post() {
    }

    @OneToOne(fetch = FetchType.EAGER)
    private Image image;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    private User poster;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Set<Like> likes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public int getMeLiked() {
        return meLiked;
    }

    public void setMeLiked(int meLiked) {
        this.meLiked = meLiked;
    }

    public String getPostImageURL() {
        return postImageURL;
    }

    public void setPostImageURL(String postImageURL) {
        this.postImageURL = postImageURL;
    }
}
