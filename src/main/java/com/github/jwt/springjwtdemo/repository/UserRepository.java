package com.github.jwt.springjwtdemo.repository;

import com.github.jwt.springjwtdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    User findByEmailIgnoreCase(String userName);
}
