package com.github.jwt.springjwtdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class SpringJwtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtDemoApplication.class, args);
    }


//    public CorsFilter corsFilter() {
//
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        final CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowCredentials(true);
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedHeader("Access-Control-Allow-Origin");
//        configuration.addAllowedOrigin("http://localhost:4200");
//        configuration.addAllowedMethod("OPTIONS");
//        configuration.addAllowedMethod("POST");
//        configuration.addAllowedMethod("GET");
//        configuration.addAllowedMethod("PUT");
//        configuration.addAllowedMethod("DELETE");
//
//
//        source.registerCorsConfiguration("/**", configuration);
//        return new CorsFilter(source);
//    }

}
