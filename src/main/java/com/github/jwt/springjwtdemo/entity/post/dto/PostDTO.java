package com.github.jwt.springjwtdemo.entity.post.dto;

import com.github.jwt.springjwtdemo.entity.post.model.ContentStatus;
import com.github.jwt.springjwtdemo.entity.post.model.ContentType;
import com.github.jwt.springjwtdemo.entity.user.dto.UserDTO;
import lombok.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PostDTO {



    private Long id;
    private String content;
    private LocalDateTime creationDate;
    private ContentStatus status;
    private ContentType type;
    private LocalDateTime editionDate;
    private Integer version;
    private UserDTO userDTO;
}
