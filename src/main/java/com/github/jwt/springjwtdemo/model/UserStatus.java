package com.github.jwt.springjwtdemo.model;

public enum UserStatus {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED");

    private String userStatus;

    private UserStatus (String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
