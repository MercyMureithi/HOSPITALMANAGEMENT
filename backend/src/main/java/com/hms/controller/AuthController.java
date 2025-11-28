package com.hms.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    // Simple status endpoint to check if the auth system is working
    // I use this to make sure the server started up correctly
    @GetMapping("/status")
    public String status() {
        return "Auth status endpoint ready";
    }

    // Login endpoint - would handle username/password validation here
    // For the college demo, I kept it simple since security is disabled
    @PostMapping("/login")
    public String login() {
        return "Login endpoint - ready for implementation";
    }
}
