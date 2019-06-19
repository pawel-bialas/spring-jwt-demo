package com.github.jwt.springjwtdemo.repository;

import com.github.jwt.springjwtdemo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Iterable<Post> findByAccountId (Long id);
    Optional<Post> findById (Long id);
}
