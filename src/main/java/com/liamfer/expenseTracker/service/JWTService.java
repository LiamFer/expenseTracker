package com.liamfer.expenseTracker.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTService {
    @Value("${spring.jwt.secret}")
    private String secret;

    public String generateToken(UserDetails user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withIssuer("expense-api").withSubject(user.getUsername()).withExpiresAt(expiringDate()).sign(algorithm);
    }

    public String validateToken(String token){
        try{
            return JWT.require(Algorithm.HMAC256(secret)).withIssuer("expense-api").build().verify(token).getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    private Instant expiringDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.ofHours(-3));
    }
}
