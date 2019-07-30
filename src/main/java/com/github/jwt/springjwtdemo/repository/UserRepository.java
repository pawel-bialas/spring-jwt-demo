package com.github.jwt.springjwtdemo.repository;

import com.github.jwt.springjwtdemo.model.User;
import com.github.jwt.springjwtdemo.projection.UserExcerpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(excerptProjection = UserExcerpt.class)
public interface UserRepository extends JpaRepository <User, Long> {
    User findByEmailIgnoreCase(String userName);

    Optional<User> findByEmail(String email);

    Optional<User> findByUniqueAccName(String uniqueAccName);

}
