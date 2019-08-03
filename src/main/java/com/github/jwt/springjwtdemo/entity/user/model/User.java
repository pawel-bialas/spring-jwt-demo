package com.github.jwt.springjwtdemo.entity.user.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
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

    @Version
    @Column(name = "version")
    private Integer version;


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


}
