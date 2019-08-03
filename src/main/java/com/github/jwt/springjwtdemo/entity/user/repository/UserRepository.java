package com.github.jwt.springjwtdemo.entity.user.repository;

import com.github.jwt.springjwtdemo.entity.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource (excerptProjection = User.class)
public interface UserRepository extends JpaRepository <User, Long> {

    User findByEmailIgnoreCase(String userName);

    Optional<User> findByEmail(String email);

    Optional<User> findByUniqueAccName(String uniqueAccName);

}
