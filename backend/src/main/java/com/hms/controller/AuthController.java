package com.hms.controller;

import com.hms.model.LoginDTO;
import com.hms.model.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Server is picking up changes - " + System.currentTimeMillis());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginRequest) {
        log.info("Login attempt for user: {}", loginRequest.getUsername());
        
        // Simple login response for college project
        LoginResponse response = new LoginResponse();
        response.setUsername(loginRequest.getUsername());
        response.setMessage("Login successful - HMS College Project");
        response.setAuthenticated(true);
        
        log.info("Login endpoint accessed by user: {}", loginRequest.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        log.info("Logout endpoint accessed");
        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/status")
    public ResponseEntity<LoginResponse> checkAuthStatus() {
        LoginResponse response = new LoginResponse();
        response.setMessage("Auth status endpoint ready");
        response.setAuthenticated(false);
        
        return ResponseEntity.ok(response);
    }
}
