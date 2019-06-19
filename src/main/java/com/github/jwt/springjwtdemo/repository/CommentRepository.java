package com.github.jwt.springjwtdemo.repository;

import com.github.jwt.springjwtdemo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long aLong);
}
