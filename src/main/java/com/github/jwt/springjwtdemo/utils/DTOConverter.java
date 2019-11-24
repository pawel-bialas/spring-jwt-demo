package com.github.jwt.springjwtdemo.utils;

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

}
