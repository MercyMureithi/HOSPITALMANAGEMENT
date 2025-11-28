package com.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// I disabled security for the college demo - makes it much easier to present!
// In a real hospital, security would be super important for patient privacy
public class Application {
    public static void main(String[] args) {
        // This starts the Spring Boot application with embedded Tomcat server
        SpringApplication.run(Application.class, args);
    }
}
