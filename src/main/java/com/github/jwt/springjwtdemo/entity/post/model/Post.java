package com.github.jwt.springjwtdemo.entity.post.model;

import com.github.jwt.springjwtdemo.entity.user.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
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

}
