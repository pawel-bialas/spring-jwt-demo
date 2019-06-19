package com.github.jwt.springjwtdemo.controller;

import com.github.jwt.springjwtdemo.domain.Response;
import com.github.jwt.springjwtdemo.model.User;
import com.github.jwt.springjwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    public ResponseEntity<Response> register (@RequestBody User user) {
        User dbUser = userService.saveUser(user);
        if (dbUser != null) {
            return new ResponseEntity<Response>(new Response("User saved!"), HttpStatus.CREATED);
        }
        return null;
    }
}