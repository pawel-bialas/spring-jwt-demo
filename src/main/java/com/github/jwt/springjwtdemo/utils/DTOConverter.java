package com.github.jwt.springjwtdemo.utils;

import com.github.jwt.springjwtdemo.entity.comment.dto.CommentDTO;
import com.github.jwt.springjwtdemo.entity.comment.model.Comment;
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


    public UserDTO convertEntityToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertDTOToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public PostDTO convertEntityToDTO(Post post) {
        UserDTO userDTO = convertEntityToDTO(post.getUser());
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        postDTO.setUserDTO(userDTO);
        return postDTO;
    }

    public Post convertDTOToEntity(PostDTO postDTO) {
        User user = null;
        if (!(postDTO.getUserDTO() == null)) {
            user = convertDTOToEntity(postDTO.getUserDTO());
        }
        Post post = modelMapper.map(postDTO, Post.class);
        post.setUser(user);
        return post;
    }

    public Comment convertDTOToEntity(CommentDTO commentDTO) {
        User user = null;
        if (!(commentDTO.getUserDTO() == null)) {
            user = convertDTOToEntity(commentDTO.getUserDTO());
        }
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setUser(user);
        return comment;
    }

    public CommentDTO convertEntityToDTO (Comment comment) {
        UserDTO userDTO = convertEntityToDTO(comment.getUser());
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        commentDTO.setUserDTO(userDTO);
        return commentDTO;
    }

}



