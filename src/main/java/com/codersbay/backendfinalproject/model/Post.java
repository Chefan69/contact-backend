package com.codersbay.backendfinalproject.model;

import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String postContent;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
