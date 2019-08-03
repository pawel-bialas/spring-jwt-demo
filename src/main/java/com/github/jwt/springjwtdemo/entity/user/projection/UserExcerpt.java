package com.github.jwt.springjwtdemo.entity.user.projection;

import com.github.jwt.springjwtdemo.entity.user.model.AccountType;
import com.github.jwt.springjwtdemo.entity.user.model.UserRole;
import com.github.jwt.springjwtdemo.entity.user.model.UserStatus;

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
