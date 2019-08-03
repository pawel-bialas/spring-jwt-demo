package com.github.jwt.springjwtdemo.entity.admin.service;

import com.github.jwt.springjwtdemo.entity.comment.model.Comment;
import com.github.jwt.springjwtdemo.entity.comment.repository.CommentRepository;
import com.github.jwt.springjwtdemo.entity.post.model.ContentStatus;
import com.github.jwt.springjwtdemo.entity.post.repository.PostRepository;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.user.model.UserRole;
import com.github.jwt.springjwtdemo.entity.user.model.UserStatus;
import com.github.jwt.springjwtdemo.entity.user.repository.UserRepository;
import com.github.jwt.springjwtdemo.entity.post.service.PostService;
import com.github.jwt.springjwtdemo.entity.user.service.UserService;
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

    public void adminDeleteComment(Long commentId) {
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

    public void adminEditPost(Long postId, String content) {
        try {
            if (postRepository.findById(postId).isPresent()) {
                postService.editPostContent(postId, content);
                LOG.info("post: " + postId + " was edited by: admin");
            } else throw new EntityNotFoundException(SystemMessage.postNotFoundError);
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

    public void promoteAdmin(String userLogin) {
        try {
            User user = userRepository.findByEmailIgnoreCase(userLogin);
            if (user != null) {
                if (!user.getRole().equals(UserRole.ADMIN)) {
                    user.setRole(UserRole.ADMIN);
                    userRepository.save(user);
                } else throw new Exception("This is already an admin's account!");
            } else throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (Exception alreadyAdmin) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, alreadyAdmin.getMessage());
        }
    }


    public void removeAdmin(String userLogin) {
        try {
            User user = userRepository.findByEmailIgnoreCase(userLogin);
            if (user != null) {
                if (!user.getRole().equals(UserRole.USER)) {
                    user.setRole(UserRole.USER);
                    userRepository.save(user);
                } else throw new Exception("This is already an user's account!");
            } else throw new EntityNotFoundException(SystemMessage.userNotFoundError);
        } catch (Exception alreadyUser) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, alreadyUser.getMessage());
        }
    }
}
