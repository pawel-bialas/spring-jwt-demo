package com.github.jwt.springjwtdemo.service;

import com.github.jwt.springjwtdemo.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {

    User save(User user);
}
