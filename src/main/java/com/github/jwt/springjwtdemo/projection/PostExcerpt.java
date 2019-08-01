package com.github.jwt.springjwtdemo.projection;

import com.fasterxml.jackson.annotation.JsonValue;
import com.github.jwt.springjwtdemo.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "PostExcerpt", types = Post.class)
public interface PostExcerpt {

    Long getId();

    String getContent();

    LocalDateTime getCreationDate();

    LocalDateTime getEditionDate();


    @Value("#{target.user.uniqueAccName}")
    String getUniqueAccName();

    @Value("#{target.user.descAccName}")
    String getDescAccName();

    @Value("#{target.user.registerDate}")
    LocalDateTime getRegisterDate();

    @Value("#{target.user.status}")
    String getStatus();

    @Value("#{target.user.type}")
    String getType();

    @Value("#{target.user.avatarId}")
    Long getAvatarId();

    @Value("#{target.user.role}")
    String getRole();


}
