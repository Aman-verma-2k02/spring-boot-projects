package com.project.spring_boot.UserAuthenticationSystem.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey;

    private SecretKey getKey() {
        byte[] keyByte = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String generateJwtToken(String username) {
        return Jwts
                .builder()
                .issuer("ADMIN")
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 600_000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractClaim(String jwtToken) {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    public String extractUsername(String jwtToken) {
        Claims claims = extractClaim(jwtToken);
        return claims.getSubject();
    }

    private boolean isExpired(String jwtToken) {
        Claims claims = extractClaim(jwtToken);
        return  claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public boolean isValidToken(String jwtToken) {
        return (!isExpired(jwtToken));
    }
}
