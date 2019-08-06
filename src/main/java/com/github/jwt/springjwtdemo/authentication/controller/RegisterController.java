package com.github.jwt.springjwtdemo.authentication.controller;

import com.github.jwt.springjwtdemo.utils.Response;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.user.service.UserService;
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
    public ResponseEntity<Response> register(@RequestBody User user) {
        User dbUser = userService.saveUser(user);
        if (dbUser != null) {
            return new ResponseEntity<>(new Response("User saved!"), HttpStatus.CREATED);
        } else return null;
    }

    @PostMapping(value = "/register/login")
    public ResponseEntity<Boolean> checkAvailableLogin(@RequestBody String login) {
        return new ResponseEntity<>(userService.availableLogin(login), HttpStatus.OK);
    }

    @PostMapping(value = "/register/unique-acc-name")
    public ResponseEntity<Boolean> checkAvailableUniqueAccName(@RequestBody String uniqueAccName) {
        return new ResponseEntity<>(userService.availableUniqueAccName(uniqueAccName), HttpStatus.OK);
    }

    @PostMapping(value = "/login/check-login")
    public ResponseEntity<Boolean> checkLoginWhileSignIn(@RequestBody String login) {
        return new ResponseEntity<>(userService.checkLoginWhileSignIn(login), HttpStatus.OK);
    }
}
