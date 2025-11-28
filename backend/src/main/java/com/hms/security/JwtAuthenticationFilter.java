package com.hms.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    
    // I kept this simple for the college demo - security is disabled anyway
    // In a real hospital app, this would be super important for patient privacy!
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                  @NonNull HttpServletResponse response, 
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        // Since we disabled security for the demo, this just logs the request
        // Took me forever to figure out I could just disable security entirely!
        log.info("JWT Filter processing request: {}", request.getRequestURI());
        
        // If this was a real hospital system, I would:
        // 1. Get the JWT token from the Authorization header
        // 2. Check if the token is valid (not expired, correct signature)
        // 3. Load the doctor/patient user info from the token
        // 4. Make sure they can only access their own data!
        
        // For now, just let everything through - much easier for college demo
        filterChain.doFilter(request, response);
    }
}