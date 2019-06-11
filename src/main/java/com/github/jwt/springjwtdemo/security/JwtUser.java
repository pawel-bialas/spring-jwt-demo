package com.github.jwt.springjwtdemo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.jwt.springjwtdemo.model.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String userName;
    private final String password;
    private final User user;
    private final Collection <? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public JwtUser(Long id, String userName, String password, User user, Collection<? extends GrantedAuthority> authorities, boolean enabled) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.user = user;
        this.authorities = authorities;
        this.enabled = enabled;
    }
    @JsonIgnore
    public Long getId() {
        return id;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
