package com.codersbay.backendfinalproject.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String name;

    private String email;

    @ManyToMany(mappedBy= "contacts")
  //  @JsonBackReference
    @JsonIgnore
    private Set<User> users = new HashSet<>();




    public void setUsers(Set<User> users) {

        this.users = users;
    }



    public Set<User> getUsers() {
        return users;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
