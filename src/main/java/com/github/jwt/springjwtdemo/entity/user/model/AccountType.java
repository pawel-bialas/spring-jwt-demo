package com.github.jwt.springjwtdemo.entity.user.model;

public enum AccountType {

    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE");


    private String accountType;

    private AccountType (String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
