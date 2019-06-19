package com.github.jwt.springjwtdemo.service;

import com.github.jwt.springjwtdemo.model.AccountType;
import com.github.jwt.springjwtdemo.model.User;
import com.github.jwt.springjwtdemo.model.UserRole;
import com.github.jwt.springjwtdemo.model.UserStatus;
import com.github.jwt.springjwtdemo.repository.UserRepository;
import com.github.jwt.springjwtdemo.utils.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    private Logger LOG = Logger.getLogger(UserService.class.getName());





    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        user.setType(AccountType.PUBLIC);
        if (user.getRole() == null) {
            user.setRole(UserRole.USER);
        }
        User dbUser = userRepository.save(user);
        return dbUser;
    }

    public List<User> getUsers() {
        try {
            return userRepository.findAll();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.userNotFoundError
            );
        }

    }


    public User findUserById(Long id) {
        try {
            Optional<User> byId = userRepository.findById(id);
            if (byId.isPresent()) {
                return byId.get();
            } else throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (EntityNotFoundException notFound) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.userNotFoundError
            );
        }

    }

    public User findUserByLogin(String login) {
        try {
            Optional<User> byLogin = userRepository.findByLogin(login);
            if (byLogin.isPresent()) {
                return byLogin.get();
            } else throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.userNotFoundError
            );
        }

    }

    public User findUserByUniqueAccName(String uniqueAccName) {
        try {
            Optional<User> byUnigueAccName = userRepository.findByUnigueAccName(uniqueAccName);
            if (byUnigueAccName.isPresent()) {
                return byUnigueAccName.get();
            } else
                LOG.warning("Error");
            throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.userNotFoundError
            );
        }

    }

    public void adminDeleteUser(Long accountId) {
        try {
            User userById = findUserById(accountId);
            if (userById != null) {
                userById.setStatus(UserStatus.BLOCKED);
                userRepository.save(userById);
                LOG.info("User login: " + userById.getEmail() + " was blocked by admin");
            } else throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (EntityNotFoundException notFound) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.userNotFoundError
            );
        }
    }



    public void deleteAccount () {

    }

    public void makeAccountPrivate () {

    }

    public void makeAccountPublic () {

    }

    public void updateUser() {

    }

    public void changePassword(String newPassword, Principal principal) {
        try {
            User user = findUserByLogin(principal.getName());
            if (user != null) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                LOG.info("Password was changed for account: " + principal.getName());
            } else throw new EntityNotFoundException();
        } catch (EntityNotFoundException notFound) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.userNotFoundError
            );
        }


    }
}