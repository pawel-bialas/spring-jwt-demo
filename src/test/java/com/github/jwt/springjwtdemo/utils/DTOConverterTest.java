package com.github.jwt.springjwtdemo.utils;

import com.github.jwt.springjwtdemo.entity.post.dto.PostDTO;
import com.github.jwt.springjwtdemo.entity.post.model.ContentStatus;
import com.github.jwt.springjwtdemo.entity.post.model.ContentType;
import com.github.jwt.springjwtdemo.entity.post.model.Post;
import com.github.jwt.springjwtdemo.entity.user.dto.UserDTO;
import com.github.jwt.springjwtdemo.entity.user.model.AccountType;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.user.model.UserRole;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DTOConverterTest {

    private DTOConverter DTOConverter = new DTOConverter();

    @Test
    void shouldResultTrueWhenCheckingUserDTOConversionObjectProperties() {
        // Given

        User user = new User();
        user.setId(1L);
        user.setEmail("abc@abc.pl");
        user.setUniqueAccName("abc");
        user.setDescAccName("abc");
        user.setRole(UserRole.USER);
        user.setType(AccountType.PUBLIC);
        // When
        UserDTO userDTO = DTOConverter.convertEntityToDTO(user);
        // Then
        assertEquals(user.getEmail(),userDTO.getEmail());
    }

    @Test
    void shouldReturnTrueWhenCheckingUserConvertedObjectProperties() {
        // Given

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("abc@cba.pl");
        userDTO.setDescAccName("abc");
        userDTO.setUniqueAccName("abc");
        userDTO.setRole(UserRole.USER);
        userDTO.setType(AccountType.PUBLIC);
        // When
        User user = DTOConverter.convertDTOToEntity(userDTO);
        // Then
        assertEquals(user.getEmail(),userDTO.getEmail());
    }

    @Test
    void shouldReturnTrueWhenCheckingPostConvertedObjectProperties() {
        // Given
        PostDTO postDTO = new PostDTO();
        postDTO.setContent("asd");
        postDTO.setType(ContentType.BLOG_POST);
        postDTO.setId(1L);
        postDTO.setStatus(ContentStatus.NEW);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("abc@cba.pl");
        userDTO.setDescAccName("abc");
        userDTO.setUniqueAccName("abc");
        userDTO.setRole(UserRole.USER);
        userDTO.setType(AccountType.PUBLIC);
        postDTO.setUserDTO(userDTO);
        // When
        Post post = DTOConverter.convertDTOToEntity(postDTO);
        // Then
        assertEquals(post.getContent(),postDTO.getContent());
        assertEquals(post.getUser().getEmail(),postDTO.getUserDTO().getEmail());
    }

    @Test
     void shouldResultTrueWhenCheckingPostDTOConversionObjectProperties() {
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
        PostDTO postDTO = DTOConverter.convertEntityToDTO(post);
        // Then
        assertEquals(post.getContent(),postDTO.getContent());
        assertEquals(post.getUser().getEmail(),postDTO.getUserDTO().getEmail());

    }




}