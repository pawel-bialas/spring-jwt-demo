package com.github.jwt.springjwtdemo.model;

import com.github.jwt.springjwtdemo.projection.UserExcerpt;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BlogPosts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "content")
    private String content;
    @CreationTimestamp
    @Column(name = "publication_date")
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "content_status")
    private ContentStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "content_type")
    private ContentType type;
    @Column(name = "author_id")
    private Long accountId;
    @Column(name = "edition_date")
    private LocalDateTime editionDate;

    @OneToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    public Post() {

    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public ContentType getType() {
        return type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(LocalDateTime editionDate) {
        this.editionDate = editionDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
