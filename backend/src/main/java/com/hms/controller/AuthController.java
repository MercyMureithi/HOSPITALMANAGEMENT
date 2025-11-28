package com.hms.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @GetMapping("/status")
    public String status() {
        return "Auth status endpoint ready";
    }

    @PostMapping("/login")
    public String login() {
        return "Login endpoint - ready for implementation";
    }
}
