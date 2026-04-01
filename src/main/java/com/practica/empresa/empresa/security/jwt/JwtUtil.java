package com.practica.empresa.empresa.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration:28800000}") // 8 horas por defecto (8 * 60 * 60 * 1000)
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /**
     * Genera el token JWT usando solo el username (como tienes actualmente)
     */
    public String generateToken(final String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el username del token
     */
    public String extractUsername(final String token) {
        return extractAllClaims(token).getSubject();
    }

    /**
     * Valida que el token pertenezca al usuario y no haya expirado
     */
    public boolean validateToken(final String token, final String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    /**
     * Extrae todos los claims (útil para futuro cuando añadas roles, email, etc.)
     */
    public Claims extractAllClaims(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(final String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    /**
     * Método útil para cuando quieras añadir más información al token en el futuro
     */
    public String generateTokenWithExtraClaims(final String username, final Claims extraClaims) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}