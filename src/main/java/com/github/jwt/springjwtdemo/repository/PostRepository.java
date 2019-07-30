package com.github.jwt.springjwtdemo.repository;

import com.github.jwt.springjwtdemo.model.Post;
import com.github.jwt.springjwtdemo.projection.PostExcerpt;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(excerptProjection = PostExcerpt.class)
public interface PostRepository extends JpaRepository<Post, Long> {

    Iterable<Post> findByAccountId (Long id);
    Optional<Post> findById (Long id);

    List<PostExcerpt> findAllBy();
}
