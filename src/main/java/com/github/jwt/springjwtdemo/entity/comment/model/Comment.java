package com.github.jwt.springjwtdemo.entity.comment.model;


import com.github.jwt.springjwtdemo.entity.post.model.ContentStatus;
import com.github.jwt.springjwtdemo.entity.post.model.ContentType;
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
@Table(name = "comments")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private ContentType type;
    @Enumerated(EnumType.STRING)
    private ContentStatus status;
    private Long accountId;
    private Long postId;
    private LocalDateTime editionDate;

    @Version
    @Column(name = "version")
    private Integer version;

    @OneToOne (cascade = {CascadeType.ALL})
    @JoinColumn(referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

}
