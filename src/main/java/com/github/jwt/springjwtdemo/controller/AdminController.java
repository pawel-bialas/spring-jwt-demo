package com.github.jwt.springjwtdemo.controller;

import com.github.jwt.springjwtdemo.model.Post;
import com.github.jwt.springjwtdemo.model.User;
import com.github.jwt.springjwtdemo.service.AdminService;
import com.github.jwt.springjwtdemo.service.PostService;
import com.github.jwt.springjwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;


    @DeleteMapping(path = "/admin/delete-user/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        adminService.adminDeleteUser(id);
    }

    @GetMapping(path = "/admin/users/all")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public List<User> adminGetAllUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/admin/find-all-posts")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> adminFindAllPosts() {
        return postService.findAllPublicPosts();
    }

    @PatchMapping (path = "/admin/edit-post/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public void adminEditPost (@PathVariable ("id") Long postId, @RequestBody String content, Principal principal) {
        adminService.adminEditPost(postId,content);
    }

    @DeleteMapping(path = "/admin/delete-post/{id}")
    @Secured("ROLE_ADMIN")
    public void adminDeletePost (@PathVariable ("id") Long postId, Principal principal) {
        adminService.adminDeletePost(postId);
    }

    @DeleteMapping(path = "/admin/delete-comment/{id}")
    @Secured("ROLE_ADMIN")
    public void adminDeleteComment (@PathVariable ("id") Long commentId) {
        adminService.adminDeleteComment(commentId);

    }
    @PatchMapping (path = "/admin/edit-comment/{id}")
    @Secured("ROLE_ADMIN")
    public void adminEditComment (@PathVariable ("id") Long commentId, String content) {
        adminService.adminEditComment(commentId,content);
    }

    @PostMapping(path = "/master/promote-admin/{login}")
    @Secured("ROLE_MASTER_ADMIN")
    public void promoteAdmin (@PathVariable("login") String userLogin) {
        adminService.promoteAdmin(userLogin);
    }

    @PostMapping(path = "/master/remove-admin/{login}")
    @Secured("ROLE_MASTER_ADMIN")
    public void removeAdmin (@PathVariable("login") String userLogin) {
        adminService.removeAdmin(userLogin);
    }




}

