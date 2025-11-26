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
    
    // For college project - simplified JWT filter
    // In real project, you would inject JwtTokenProvider and UserDetailsService
    
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                  @NonNull HttpServletResponse response, 
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        // For college project simplicity, we'll skip actual JWT validation
        // and just set up basic authentication context
        log.info("JWT Filter processing request: {}", request.getRequestURI());
        
        // In a real implementation, you would:
        // 1. Extract JWT from header
        // 2. Validate token
        // 3. Load user from token
        // 4. Set authentication context
        
        filterChain.doFilter(request, response);
    }
}