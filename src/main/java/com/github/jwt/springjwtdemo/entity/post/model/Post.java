package com.github.jwt.springjwtdemo.entity.post.model;

import com.github.jwt.springjwtdemo.entity.user.model.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blog_posts")
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
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "edition_date")
    private LocalDateTime editionDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
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

    public Long getUserId() {
        return userId;
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

    public void setUserId(Long userId) {
        this.userId = userId;
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
