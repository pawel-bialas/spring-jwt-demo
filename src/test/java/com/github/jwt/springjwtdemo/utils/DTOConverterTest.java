package com.github.jwt.springjwtdemo.utils;

import com.github.jwt.springjwtdemo.entity.comment.dto.CommentDTO;
import com.github.jwt.springjwtdemo.entity.comment.model.Comment;
import com.github.jwt.springjwtdemo.entity.post.dto.PostDTO;
import com.github.jwt.springjwtdemo.entity.post.model.ContentStatus;
import com.github.jwt.springjwtdemo.entity.post.model.ContentType;
import com.github.jwt.springjwtdemo.entity.post.model.Post;
import com.github.jwt.springjwtdemo.entity.user.dto.UserDTO;
import com.github.jwt.springjwtdemo.entity.user.model.AccountType;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.user.model.UserRole;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class DTOConverterTest {

    private DTOConverter _DTOConverter = new DTOConverter();

    @Test
    void convertingUserEntityToUserDTO() {
        // Given

        User user = new User();
        user.setId(1L);
        user.setEmail("abc@abc.pl");
        user.setUniqueAccName("abc");
        user.setDescAccName("abc");
        user.setRole(UserRole.USER);
        user.setType(AccountType.PUBLIC);
        // When
        UserDTO userDTO = _DTOConverter.convertEntityToDTO(user);
        User user1 = _DTOConverter.convertDTOToEntity(userDTO);
        System.out.println(user.toString());
        System.out.println(userDTO.toString());
        System.out.println(user1.toString());
        // Then
        assertEquals(user.getUniqueAccName(), userDTO.getUniqueAccName());
    }

    @Test
    void convertingUserDTOtoUserEntity() {
        // Given

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setDescAccName("abc");
        userDTO.setUniqueAccName("abc");
        // When
        User user = _DTOConverter.convertDTOToEntity(userDTO);
        System.out.println(user.toString());
        // Then
        assertEquals(user.getUniqueAccName(), userDTO.getUniqueAccName());
    }

    @Test
    void convertingPostDTOtoPostEntity() {
        // Given
        PostDTO postDTO = new PostDTO();
        postDTO.setContent("asd");
        postDTO.setType(ContentType.BLOG_POST);
        postDTO.setId(1L);
        postDTO.setStatus(ContentStatus.NEW);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setDescAccName("abc");
        userDTO.setUniqueAccName("abc");
        postDTO.setUserDTO(userDTO);
        // When
        Post post = _DTOConverter.convertDTOToEntity(postDTO);
        // Then
        assertEquals(post.getContent(), postDTO.getContent());
        assertEquals(post.getUser().getDescAccName(), postDTO.getUserDTO().getDescAccName());
    }

    @Test
    void convertingPostEntityToPostDTO() {
        // Given
        Post post = new Post();
        post.setContent("asd");
        post.setType(ContentType.BLOG_POST);
        post.setId(1L);
        post.setStatus(ContentStatus.NEW);
        User user = new User();
        user.setId(1L);
        user.setEmail("abc@cba.pl");
        user.setDescAccName("abc");
        user.setUniqueAccName("abc");
        user.setRole(UserRole.USER);
        user.setType(AccountType.PUBLIC);
        post.setUser(user);
        // When
        PostDTO postDTO = _DTOConverter.convertEntityToDTO(post);
        // Then
        assertEquals(post.getContent(), postDTO.getContent());
        assertEquals(post.getUser().getUniqueAccName(), postDTO.getUserDTO().getUniqueAccName());

    }

    @Test
    public void convertingCommentEntityToCommentDTO() {
        // Given
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setContent("ABCDE");
        comment.setCreationDate(LocalDateTime.now());
        User user = new User();
        user.setId(1L);
        user.setEmail("abc@cba.pl");
        user.setDescAccName("abc");
        user.setUniqueAccName("abc");
        user.setRole(UserRole.USER);
        user.setType(AccountType.PUBLIC);
        comment.setUser(user);
        comment.setAccountId(user.getId());

        // When
        CommentDTO commentDTO = _DTOConverter.convertEntityToDTO(comment);
        // Then
        assertEquals(comment.getContent(), commentDTO.getContent());
        assertEquals(comment.getCreationDate(), commentDTO.getCreationDate());
    }

    @Test
    public void convertingCommentDTOToCommentEntity() {
        // Given
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setContent("ABCDE");
        commentDTO.setCreationDate(LocalDateTime.now());
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setDescAccName("abc");
        userDTO.setUniqueAccName("abc");

        commentDTO.setUserDTO(userDTO);
        commentDTO.setAccountId(userDTO.getId());

        // When
        Comment comment = _DTOConverter.convertDTOToEntity(commentDTO);
        // Then
        assertEquals(comment.getContent(), commentDTO.getContent());
        assertEquals(comment.getCreationDate(), commentDTO.getCreationDate());

    }


}