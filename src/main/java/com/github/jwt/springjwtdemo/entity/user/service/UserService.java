package com.github.jwt.springjwtdemo.entity.user.service;

import com.github.jwt.springjwtdemo.entity.user.model.AccountType;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.user.model.UserRole;
import com.github.jwt.springjwtdemo.entity.user.model.UserStatus;
import com.github.jwt.springjwtdemo.entity.user.repository.UserRepository;
import com.github.jwt.springjwtdemo.utils.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
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

    private EntityManagerFactory emf;
    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private Logger LOG = Logger.getLogger(UserService.class.getName());

    public void defineMasterAdmin (User masterAdmin) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(masterAdmin);
        em.getTransaction().commit();
    }

    public User saveUser(User user) {
        try {
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new EntityExistsException(SystemMessage.userNameAlreadyTaken);
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setUserStatus(UserStatus.ACTIVE);
            if (user.getType() == null) {
                user.setType(AccountType.PUBLIC);
            }
            user.setRole(UserRole.USER);

            return userRepository.save(user);
        } catch (EntityExistsException duplicateUser) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    duplicateUser.getMessage()
                    );
        }

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
                    notFound.getMessage()
            );
        }

    }

    public User findUserByEmail(String email) {
        try {
            Optional<User> byLogin = userRepository.findByEmail(email);
            if (byLogin.isPresent()) {
                return byLogin.get();
            } else throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (EntityNotFoundException notFound) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    notFound.getMessage()
            );
        }

    }

    public User findUserByUniqueAccName(String uniqueAccName) {
        try {
            Optional<User> user = userRepository.findByUniqueAccName(uniqueAccName);
            if (user.isPresent()) {
                return user.get();
            } else
                LOG.warning("Error");
            throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (EntityNotFoundException notFound) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    notFound.getMessage()
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
            User user = findUserByEmail(principal.getName());
            if (user != null) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
                LOG.info("Password was changed for account: " + principal.getName());
            } else throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (EntityNotFoundException notFound) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    notFound.getMessage()
            );
        }
    }

    public Boolean availableLogin(String login) {
        User user = userRepository.findByEmailIgnoreCase(login);
        return user == null;
    }

    public Boolean availableUniqueAccName(String uniqueAccName) {
        Optional<User> byUniqueAccName = userRepository.findByUniqueAccName(uniqueAccName);
        return !byUniqueAccName.isPresent();
    }

    public Boolean checkLoginWhileSignIn (String login) {
        User user = userRepository.findByEmailIgnoreCase(login);
        return user != null;
    }
}
