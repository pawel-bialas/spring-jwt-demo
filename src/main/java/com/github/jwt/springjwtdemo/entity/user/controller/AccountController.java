package com.github.jwt.springjwtdemo.entity.user.controller;

import com.github.jwt.springjwtdemo.entity.user.dto.UserDTO;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.user.service.UserService;
import com.github.jwt.springjwtdemo.utils.DTOConverter;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class AccountController {

    private final UserService userService;

    private DTOConverter _DTOConverter = new DTOConverter();

    @Autowired
    public AccountController(UserService service) {
        this.userService = service;
    }


    @GetMapping(path = "/users/user-id/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus (HttpStatus.OK)
    public UserDTO findUserById (@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return _DTOConverter.convertEntityToDTO(user);
    }


    @GetMapping(path = "/users/user-login/{login}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findUserByLogin (@PathVariable ("login") String email) {
        User user = userService.findUserByEmail(email);
        return _DTOConverter.convertEntityToDTO(user);

    }

    @Where(clause = "user_status <> 'BLOCKED'")
    @GetMapping(path = "/users/user-uniqe/{unique}")
    @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findByUniqueName(@PathVariable ("unique") String uniqueAccName) {
        User user = userService.findUserByUniqueAccName(uniqueAccName);
        return _DTOConverter.convertEntityToDTO(user);
    }

    @Where(clause = "user_status <> 'BLOCKED'")
    @PostMapping(path = "/users/change-password")
    @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
    @ResponseStatus(HttpStatus.OK)
    public void changePassword (@RequestBody String newPassword, Principal principal) {
        userService.changePassword(newPassword, principal);
    }

}
