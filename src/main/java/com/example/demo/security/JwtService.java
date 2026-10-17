package com.example.demo.security;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.demo.user.User;

import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    private final SecretKey secretKey = Jwts.SIG.HS256.key().build();

    public String generateToken(User user) {

        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole())
                .signWith(secretKey)
                .compact();
    }

    public String extractEmail(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String extractRole(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }
}