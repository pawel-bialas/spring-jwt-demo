package com.github.jwt.springjwtdemo.repository;

import com.github.jwt.springjwtdemo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long aLong);
}
