package com.github.jwt.springjwtdemo.controller;

import com.github.jwt.springjwtdemo.model.Post;
import com.github.jwt.springjwtdemo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService service) {
        this.postService = service;
    }


    @PostMapping(path = "/post/new-post")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.CREATED)
    public void newPost(@RequestBody Post post,
                        Principal principal) {
        postService.saveNewPost(post, principal);
    }

    @PatchMapping(path = "/post/edit-post/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public void editPost(Principal principal,
                         @RequestBody String content,
                         @PathVariable("id") Long id) {
        postService.editPost(content, id, principal);
    }


    @DeleteMapping(path = "/post/delete-post/{id}")
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(Principal principal,
                           @PathVariable("id") Long id) {
        postService.deletePost(id, principal);
    }

    @GetMapping(path = "/post/get-all")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPublicPosts() {
        return postService.findAllPublicPosts();
    }

}
