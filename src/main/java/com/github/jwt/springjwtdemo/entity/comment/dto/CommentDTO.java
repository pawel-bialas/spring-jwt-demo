package com.github.jwt.springjwtdemo.entity.comment.dto;

import com.github.jwt.springjwtdemo.entity.post.model.ContentStatus;
import com.github.jwt.springjwtdemo.entity.post.model.ContentType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CommentDTO {


    private Long id;
    private String content;
    private LocalDateTime creationDate;
    private ContentType type;
    private ContentStatus status;
    private Long accountId;
    private Long postId;
    private LocalDateTime editionDate;
    private Integer version;
}
