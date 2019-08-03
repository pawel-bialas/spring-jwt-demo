package com.github.jwt.springjwtdemo.entity.post.model;

public enum  ContentStatus {

    NEW("NEW"),
    EDITED("EDITED"),
    DELETED("DELETED");

    private String contentStatus;

    private ContentStatus (String contentStatus) {
        this.contentStatus = contentStatus;
    }


    public String getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(String contentStatus) {
        this.contentStatus = contentStatus;
    }
}
