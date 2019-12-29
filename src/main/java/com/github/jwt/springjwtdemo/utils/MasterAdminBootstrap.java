package com.github.jwt.springjwtdemo.utils;

import com.github.jwt.springjwtdemo.entity.user.model.AccountType;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.user.model.UserRole;
import com.github.jwt.springjwtdemo.entity.user.model.UserStatus;
import com.github.jwt.springjwtdemo.entity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class MasterAdminBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    
    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createMasterAdmin();    
    }

    private void createMasterAdmin() {
        User masterAdmin = new User();
        masterAdmin.setId(1L);
        masterAdmin.setAvatarId(1L);
        masterAdmin.setRegisterDate(LocalDateTime.now());
        masterAdmin.setPassword("$2a$10$7jFvRqWCUwhwZhy.OrsxaO/53FKbJyAgP/Ud7c2ilkiVHwqUfM5mS");
        masterAdmin.setEmail("admin@domain.com");
        masterAdmin.setUniqueAccName("masterAdmin");
        masterAdmin.setDescAccName("realMasterAdmin");
        masterAdmin.setRole(UserRole.MASTER_ADMIN);
        masterAdmin.setType(AccountType.PUBLIC);
        masterAdmin.setUserStatus(UserStatus.ACTIVE);
        masterAdmin.setVersion(1);
        userService.defineMasterAdmin(masterAdmin);
    }
}
