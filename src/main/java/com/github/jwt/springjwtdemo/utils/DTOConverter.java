package com.github.jwt.springjwtdemo.utils;

import com.github.jwt.springjwtdemo.entity.post.dto.PostDTO;
import com.github.jwt.springjwtdemo.entity.post.model.Post;
import com.github.jwt.springjwtdemo.entity.user.dto.UserDTO;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class DTOConverter {


    private ModelMapper modelMapper = new ModelMapper();


    public UserDTO convertEntityToDTO (User user) {
       return modelMapper.map(user, UserDTO.class);
    }

    public User convertDTOToEntity (UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public PostDTO convertEntityToDTO (Post post) {
        UserDTO userDTO = convertEntityToDTO(post.getUser());
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        postDTO.setUserDTO(userDTO);
        return postDTO;
    }

    public Post convertDTOToEntity (PostDTO postDTO) {
        User user = null;
        if (!(postDTO.getUserDTO() == null)) {
            user = convertDTOToEntity(postDTO.getUserDTO());
        }
        Post post = modelMapper.map(postDTO, Post.class);
        post.setUser(user);
        return post;
    }


}
