package com.github.jwt.springjwtdemo.projection;

import com.github.jwt.springjwtdemo.model.AccountType;
import com.github.jwt.springjwtdemo.model.User;
import com.github.jwt.springjwtdemo.model.UserRole;
import com.github.jwt.springjwtdemo.model.UserStatus;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

//@Projection(name = "userExcerpt", types = User.class)
public interface UserExcerpt {

    Long getId();

    String getUniqueAccName();

    String getDescAccName();

    LocalDateTime getRegisterDate();

    UserStatus getStatus();

    AccountType getType();

    Long getAvatarId();

    UserRole getRole();
}
