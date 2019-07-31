package com.github.jwt.springjwtdemo.projection;

import com.github.jwt.springjwtdemo.model.Post;
import com.github.jwt.springjwtdemo.model.User;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "PostExcerpt", types = Post.class)
public interface PostExcerpt {

    Long getId();
    String getContent();
    LocalDateTime getCreationDate();
    LocalDateTime getEditionDate();
    
}
