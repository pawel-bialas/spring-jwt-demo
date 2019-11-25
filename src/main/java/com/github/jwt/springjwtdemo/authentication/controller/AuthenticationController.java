package com.github.jwt.springjwtdemo.authentication.controller;

import com.github.jwt.springjwtdemo.authentication.model.JWTUserDTO;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.authentication.model.JwtTokenUtil;
import com.github.jwt.springjwtdemo.authentication.model.JwtUser;
import com.github.jwt.springjwtdemo.entity.user.service.UserService;
import com.github.jwt.springjwtdemo.utils.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController  {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JWTUserDTO> login (@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            final JwtUser userDetails = (JwtUser) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenUtil.generateToken(userDetails);
            response.setHeader("Token", token);
            return new ResponseEntity<>(new JWTUserDTO(userDetails.getUser(), token), HttpStatus.CREATED);

        } catch (Exception e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    @PostMapping(value = "/login/check-login")
    public ResponseEntity<Boolean> checkLoginWhileSignIn(@RequestBody String login) {
        return new ResponseEntity<>(userService.checkLoginWhileSignIn(login), HttpStatus.OK);
    }

}
