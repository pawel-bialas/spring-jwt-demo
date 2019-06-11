package com.github.jwt.springjwtdemo.service;

import com.github.jwt.springjwtdemo.model.User;
import com.github.jwt.springjwtdemo.repository.UserRepository;
import com.github.jwt.springjwtdemo.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        String password = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(password);
        user.setCreationDate(new Date());
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }


}
