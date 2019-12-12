package com.github.jwt.springjwtdemo.entity.user.dto;

import com.github.jwt.springjwtdemo.entity.user.model.AccountType;
import com.github.jwt.springjwtdemo.entity.user.model.UserRole;
import com.github.jwt.springjwtdemo.entity.user.model.UserStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDTO {


    private Long id;
    private String uniqueAccName;
    private String descAccName;
    private LocalDateTime registerDate;
    private UserStatus userStatus;
    private Long avatarId;

}
