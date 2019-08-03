package com.github.jwt.springjwtdemo.entity.post.service;


import com.github.jwt.springjwtdemo.entity.post.model.ContentStatus;
import com.github.jwt.springjwtdemo.entity.post.model.ContentType;
import com.github.jwt.springjwtdemo.entity.post.model.Post;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.post.projection.PostExcerpt;
import com.github.jwt.springjwtdemo.entity.post.repository.PostRepository;
import com.github.jwt.springjwtdemo.entity.user.repository.UserRepository;
import com.github.jwt.springjwtdemo.utils.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.security.auth.message.AuthException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private Logger LOG = Logger.getLogger(PostService.class.getName());

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post saveNewPost(Post post, Principal principal) {
        try {
            Long accountId = userRepository.findByEmailIgnoreCase(principal.getName()).getId();
            if (accountId != null) {
                post.setStatus(ContentStatus.NEW);
                post.setType(ContentType.BLOG_POST);
                post.setUserId(accountId);
                postRepository.save(post);
                LOG.info(SystemMessage.newBlogPostMsg + principal.getName());
                return post;
            } else throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (EntityNotFoundException e) {
            LOG.info(SystemMessage.badRequestError + principal.getName());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.postNotFoundError
            );
        }
    }

    public List<PostExcerpt> findAllPublicPosts() {

        return (List<PostExcerpt>) postRepository.getAllBy();

    }

    public void deletePost(Long postId, Principal principal) {
        try {
            Long deletingAccountId = userRepository.findByUniqueAccName(principal.getName()).get().getId();
            Long authorAccountId = postRepository.findById(postId).get().getUserId();
            Long currentPostId = postRepository.findById(postId).get().getId();
            if (Objects.equals(authorAccountId, deletingAccountId)) {
                if (currentPostId != null) {
                    markPostAsDeleted(postId);
                    LOG.info("post: " + postId + " was deleted by: " + principal.getName());
                } else throw new EntityNotFoundException();
            } else throw new AuthException();
        } catch (AuthException authError) {
            LOG.info(SystemMessage.unauthorizedRequestError + principal.getName());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, SystemMessage.unauthorizedRequestError);
        } catch (EntityNotFoundException notFound){
            LOG.info(SystemMessage.badRequestError + principal.getName());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.postNotFoundError
            );
        }
    }

    public void editPost(String content, Long postId, Principal principal) {
        try {
            Long currentPost = postRepository.getOne(postId).getId();
            if (currentPost != null) {
                Long authorAccountId = postRepository.getOne(currentPost).getUserId();
                Long editorAccountId = userRepository.findByUniqueAccName(principal.getName()).get().getId();
                if (Objects.equals(editorAccountId, authorAccountId)) {
                    editPostContent(postId,content);
                    LOG.info("post: " + currentPost + " was edited by: " + principal.getName());
                } else throw new AuthException();
            } else throw new EntityNotFoundException();
        } catch (AuthException authError) {
            LOG.info(SystemMessage.unauthorizedRequestError + principal.getName());
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    SystemMessage.unauthorizedRequestError + principal.getName());
        } catch (EntityNotFoundException notFound) {
            LOG.info(SystemMessage.badRequestError + principal.getName());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.badRequestError + principal.getName()
            );
        }
    }

    public ArrayList<Post> findPostsByUserLogin(String email) {
        try {
            Optional<User> byLogin = userRepository.findByEmail(email);
            if (byLogin.isPresent()) {
                Long userId = byLogin.get().getId();
                return (ArrayList<Post>) postRepository.findByUserId(userId);
            }
            throw new EntityNotFoundException();
        } catch (EntityNotFoundException e) {
            LOG.info(SystemMessage.postsListNotFoundError);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.postsListNotFoundError
            );
        }

    }

    public Post findPostById(Long postId) {
        try {
            Optional<Post> post = postRepository.findById(postId);
            if (post.isPresent()) {
                return post.get();
            } else throw new EntityNotFoundException();
        } catch (EntityNotFoundException e) {
            LOG.info(SystemMessage.postNotFoundError);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    SystemMessage.postNotFoundError
            );
        }

    }

    public void editPostContent (Long postId, String content) {
        Post post = postRepository.findById(postId).get();
        post.setContent(content);
        post.setStatus(ContentStatus.EDITED);
        post.setEditionDate(LocalDateTime.now());
        postRepository.save(post);
    }

    public void markPostAsDeleted (Long postId){
        Post deletedPost = postRepository.getOne(postId);
        deletedPost.setEditionDate(LocalDateTime.now());
        deletedPost.setStatus(ContentStatus.DELETED);
        postRepository.save(deletedPost);
    }

}
