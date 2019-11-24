package com.github.jwt.springjwtdemo.utils;

import com.github.jwt.springjwtdemo.entity.user.dto.UserDTO;
import com.github.jwt.springjwtdemo.entity.user.model.AccountType;
import com.github.jwt.springjwtdemo.entity.user.model.User;
import com.github.jwt.springjwtdemo.entity.user.model.UserRole;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class DTOConverterTest {

    @Test
    public void shouldResultTrueWhenCheckingUserDTOConversionObjectProperties() {
        // Given
        DTOConverter dtoConverter = new DTOConverter();
        User user = new User();
        user.setId(1L);
        user.setEmail("abc@abc.pl");
        user.setUniqueAccName("abc");
        user.setDescAccName("abc");
        user.setRole(UserRole.USER);
        user.setType(AccountType.PUBLIC);
        // When
        UserDTO userDTO = dtoConverter.convertEntityToDTO(user);
        // Then
        assertEquals(user.getEmail(),userDTO.getEmail());
    }

    @Test
    public void shouldReturnTrueWhenCheckingEntityConvertedObjectProperties() {
        // Given
        DTOConverter dtoConverter = new DTOConverter();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setEmail("abc@cba.pl");
        userDTO.setDescAccName("abc");
        userDTO.setUniqueAccName("abc");
        userDTO.setRole(UserRole.USER);
        userDTO.setType(AccountType.PUBLIC);
        // When
        User user = dtoConverter.convertDTOToEntity(userDTO);
        // Then
        assertEquals(user.getEmail(),userDTO.getEmail());
    }




}