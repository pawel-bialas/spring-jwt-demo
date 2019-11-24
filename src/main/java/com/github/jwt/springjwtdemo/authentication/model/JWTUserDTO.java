package com.github.jwt.springjwtdemo.authentication.model;

import com.github.jwt.springjwtdemo.entity.user.model.User;

import java.io.Serializable;

public class JWTUserDTO implements Serializable {

    private User user;
    private String token;

    public JWTUserDTO(User user, String token) {
        super();
        this.user = user;
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
