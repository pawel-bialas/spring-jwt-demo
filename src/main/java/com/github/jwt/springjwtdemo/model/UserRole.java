package com.github.jwt.springjwtdemo.model;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN"),
    MASTER_ADMIN("MASTER_ADMIN");

    private String userRole;

    private UserRole (String userRole) {
        this.userRole = userRole;
    }


    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
