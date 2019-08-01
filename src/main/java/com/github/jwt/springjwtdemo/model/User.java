package com.github.jwt.springjwtdemo.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
//@Where(clause = "user_status <> 'BLOCKED'" )
public class User implements UserDetails {

    private static final long serialVersionUID = 641597340859104987L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "unique_account_name",nullable = false, unique = true)
    private String uniqueAccName;
    @Column(name = "descriptive_account_name")
    private String descAccName;
    @CreationTimestamp
    @Column(name = "register_date")
    private LocalDateTime registerDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType type;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role;
    @Column(name = "avatar_id")
    private Long avatarId;

    public User(String email,
                String password,
                String uniqueAccName,
                String descAccName,
                LocalDateTime registerDate,
                UserStatus status,
                AccountType type,
                UserRole role,
                Long avatarId) {
        this.email = email;
        this.password = password;
        this.uniqueAccName = uniqueAccName;
        this.descAccName = descAccName;
        this.registerDate = registerDate;
        this.status = status;
        this.type = type;
        this.role = role;
        this.avatarId = avatarId;
    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return uniqueAccName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != UserStatus.BLOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == UserStatus.ACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUniqueAccName() {
        return uniqueAccName;
    }

    public void setUniqueAccName(String uniqueAccName) {
        this.uniqueAccName = uniqueAccName;
    }

    public String getDescAccName() {
        return descAccName;
    }

    public void setDescAccName(String descAccName) {
        this.descAccName = descAccName;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", uniqueAccName='" + uniqueAccName + '\'' +
                ", descAccName='" + descAccName + '\'' +
                ", registerDate=" + registerDate +
                ", status=" + status +
                ", type=" + type +
                ", role=" + role +
                ", avatarId=" + avatarId +
                '}';
    }
}
