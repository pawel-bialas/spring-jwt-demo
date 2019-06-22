package com.github.jwt.springjwtdemo.service;

import com.github.jwt.springjwtdemo.model.Comment;
import com.github.jwt.springjwtdemo.model.ContentStatus;
import com.github.jwt.springjwtdemo.model.User;
import com.github.jwt.springjwtdemo.model.UserStatus;
import com.github.jwt.springjwtdemo.repository.CommentRepository;
import com.github.jwt.springjwtdemo.repository.PostRepository;
import com.github.jwt.springjwtdemo.repository.UserRepository;
import com.github.jwt.springjwtdemo.utils.SystemMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
@Transactional
public class AdminService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    private Logger LOG = Logger.getLogger(AdminService.class.getName());

    public void adminDeleteComment (Long commentId) {
        try {
            if (commentRepository.findById(commentId).isPresent()) {
                Comment comment = commentRepository.getOne(commentId);
                comment.setStatus(ContentStatus.DELETED);
                comment.setEditionDate(LocalDateTime.now());
                commentRepository.save(comment);
            } else throw new EntityNotFoundException(SystemMessage.commentNotFoundError);
        } catch (EntityNotFoundException notFound) {
            LOG.info(SystemMessage.commentNotFoundError);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    notFound.getMessage()
            );
        }
    }

    public void adminDeleteUser(Long accountId) {
        try {
            User userById = userService.findUserById(accountId);
            if (userById != null) {
                userById.setStatus(UserStatus.BLOCKED);
                userRepository.save(userById);
                LOG.info("User login: " + userById.getEmail() + " was blocked by admin");
            } else throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (EntityNotFoundException notFound) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    notFound.getMessage()
            );
        }
    }

    public void adminEditPost (Long postId, String content) {
        try {
            if (postRepository.findById(postId).isPresent()) {
                postService.editPostContent(postId,content);
                LOG.info("post: " + postId + " was edited by: admin");
            } else throw new EntityNotFoundException( SystemMessage.postNotFoundError);
        } catch (EntityNotFoundException notFound) {
            LOG.info(SystemMessage.postNotFoundError);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    notFound.getMessage()
            );
        }
    }

    public void adminDeletePost(Long postId) {
        try {
            if (postRepository.findById(postId).isPresent()) {
                postService.markPostAsDeleted(postId);
                LOG.info("post: " + postId + " was deleted by: admin");
            } else throw new EntityNotFoundException(SystemMessage.postNotFoundError);
        } catch (EntityNotFoundException notFound) {
            LOG.info(SystemMessage.postNotFoundError);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    notFound.getMessage()
            );
        }
    }


    public void adminEditComment(Long commentId, String content) {
        try {
            if (commentRepository.findById(commentId).isPresent()) {
                Comment editedComment = commentRepository.getOne(commentId);
                editedComment.setContent(content);
                commentRepository.save(editedComment);
                LOG.info("comment " + commentId + " was edited by: admin");
            } else throw new EntityNotFoundException(SystemMessage.commentNotFoundError);
        } catch (EntityNotFoundException notFound) {
            LOG.info(SystemMessage.commentNotFoundError);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    notFound.getMessage()
            );
        }
    }
}
