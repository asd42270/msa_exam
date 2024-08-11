package com.spring_cloud.eureka.client.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class AuthService {

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long expiration;
    private final Key secretKey;

    public AuthService(@Value("${service.jwt.secret-key}") String secretKey) {
        // Ensure the secret key is correctly decoded and of the appropriate length
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String createAccessToken(String user_id) {
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .claim("user_id", user_id)
                .issuer(issuer)
                .claim("roel", "ADMIN")
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)  // Ensure the correct order of parameters
                .compact();
    }
}
