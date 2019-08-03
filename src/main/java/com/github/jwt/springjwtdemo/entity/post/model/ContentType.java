package com.github.jwt.springjwtdemo.entity.post.model;

public enum  ContentType {

    BLOG_POST("BLOG_POST"),
    COMMENT("COMMENT"),
    FORWARDED("FORWARDED");

    private String contentType;

    private ContentType (String contentType) {
        this.contentType = contentType;
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
