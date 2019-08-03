package com.github.jwt.springjwtdemo.authentication.model;

import com.github.jwt.springjwtdemo.authentication.model.JwtUser;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFacory {
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user,
                mapToGrantedAuthorities(new ArrayList<String>(Arrays.asList("ROLE_" + user.getRole()))),
                user.isEnabled());
    }

//    TODO check eventually arrow function
    private static List<GrantedAuthority> mapToGrantedAuthorities(ArrayList<String> authorities) {
        return authorities.stream().map(Authority -> new SimpleGrantedAuthority(Authority)).collect(Collectors.toList());
    }
}
