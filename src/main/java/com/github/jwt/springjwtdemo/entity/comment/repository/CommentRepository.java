package com.github.jwt.springjwtdemo.entity.comment.repository;

import com.github.jwt.springjwtdemo.entity.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findById(Long aLong);
}
