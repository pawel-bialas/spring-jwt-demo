package com.github.jwt.springjwtdemo.security;

import com.github.jwt.springjwtdemo.model.User;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
                mapToGrantedAuthorities(new ArrayList<String>(Arrays.asList("ROLE" + user.getRole()))),
                user.isEnabled());
    }

//    TODO check eventually arrow function
    private static List<GrantedAuthority> mapToGrantedAuthorities(ArrayList<String> authorities) {
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
