package com.github.jwt.springjwtdemo.entity.post.repository;

import com.github.jwt.springjwtdemo.entity.post.model.Post;
import com.github.jwt.springjwtdemo.entity.post.projection.PostExcerpt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource (excerptProjection = PostExcerpt.class)
public interface PostRepository extends JpaRepository<Post, Long> {

    Iterable<Post> findByUserId (Long id);
    Optional<Post> findById (Long id);

    Iterable<PostExcerpt> getAllByOrderByCreationDateDesc();



}
