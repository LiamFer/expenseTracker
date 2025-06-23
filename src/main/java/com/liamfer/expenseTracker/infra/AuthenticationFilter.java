package com.liamfer.expenseTracker.infra;

import com.liamfer.expenseTracker.repository.UserRepository;
import com.liamfer.expenseTracker.service.AuthService;
import com.liamfer.expenseTracker.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private UserRepository userRepository;

    public AuthenticationFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        try {
            if (header != null) {
                String token = header.replace("Bearer ", "");
                logger.info(token);
                String email = jwtService.validateToken(token);
                if(email == null) throw new Exception("Token Inválido");
                UserDetails userData = userRepository.findByEmail(email);
                Authentication authToken = new UsernamePasswordAuthenticationToken(userData, null, userData.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        }
        catch(Exception e){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("{\"code\": \"401\",\"message\": \"Token invalido\"}");

        }
    }
}
