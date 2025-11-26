package com.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
//@EnableMethodSecurity(prePostEnabled = true) // Disabled for college project demo
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
