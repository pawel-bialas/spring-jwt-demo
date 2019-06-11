package com.github.jwt.springjwtdemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.user = user;
        this.authorities = authorities;
        this.enabled = enabled;
    }
}
