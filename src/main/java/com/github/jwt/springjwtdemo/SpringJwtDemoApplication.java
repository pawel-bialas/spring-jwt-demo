package com.github.jwt.springjwtdemo;

import com.github.jwt.springjwtdemo.model.User;
import com.github.jwt.springjwtdemo.model.UserRole;
import com.github.jwt.springjwtdemo.model.UserStatus;
import com.github.jwt.springjwtdemo.repository.UserRepository;
import com.github.jwt.springjwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class SpringJwtDemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(SpringJwtDemoApplication.class, args);

    }


}
