package com.github.jwt.springjwtdemo.entity.comment.controller;

import com.github.jwt.springjwtdemo.entity.comment.model.Comment;
import com.github.jwt.springjwtdemo.entity.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;

    }


    @PostMapping(path = "/comment/new-comment/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewComment(@RequestBody Comment comment, @PathVariable("id") Long postId, Principal principal) {
        commentService.saveNewComment(comment, postId, principal);

    }

    @DeleteMapping(path = "/comment/delete-comment/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(Principal principal, @PathVariable("id") Long commentId) {
        commentService.deleteComment(principal,commentId);
    }

}
