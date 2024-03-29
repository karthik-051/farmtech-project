package com.mitron.onlinestore.domain.models.service;


import java.util.HashSet;
import java.util.Set;

import com.mitron.onlinestore.domain.entities.*;

public class UserServiceModel extends BaseServiceModel {

    private String username;
    private String password;
    private String address;
    private String email;
    private Set<Role> authorities;


    public UserServiceModel() {
        this.authorities = new HashSet<>();

    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
