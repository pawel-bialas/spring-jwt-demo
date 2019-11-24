package com.github.jwt.springjwtdemo.entity.user.controller;

import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.user.service.UserService;
import org.hibernate.annotations.Where;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class AccountController {

    private final UserService userService;

    @Autowired
    public AccountController(UserService service) {
        this.userService = service;
    }


    @GetMapping(path = "/users/user-id/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus (HttpStatus.OK)
    public User findUserById (@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }


    @GetMapping(path = "/users/user-login/{login}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public User findUserByLogin (@PathVariable ("login") String email) {

        return userService.findUserByEmail(email);
    }

    @Where(clause = "user_status <> 'BLOCKED'")
    @GetMapping(path = "/users/user-uniqe/{unique}")
    @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
    @ResponseStatus(HttpStatus.OK)
    public User findByUniqueName(@PathVariable ("unique") String uniqueAccName) {
        return userService.findUserByUniqueAccName(uniqueAccName);
    }

    @Where(clause = "user_status <> 'BLOCKED'")
    @PostMapping(path = "/users/change-password")
    @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
    @ResponseStatus(HttpStatus.OK)
    public void changePassword (@RequestBody String newPassword, Principal principal) {
        userService.changePassword(newPassword, principal);
    }

}
