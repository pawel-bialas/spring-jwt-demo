package com.github.jwt.springjwtdemo.repository;

import com.github.jwt.springjwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    User findByEmailIgnoreCase(String userName);

    Optional<User> findByEmail(String email);

    Optional<User> findByUnigueAccName(String uniqueAccName);
}
