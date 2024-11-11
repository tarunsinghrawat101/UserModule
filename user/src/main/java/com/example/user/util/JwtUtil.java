package com.example.user.util;

import com.example.user.constants.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil implements Serializable {
    @Serial
    private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
    public static final long TOKEN_VALIDITY = Constants.TOKEN_VALIDITY;

    @Value("${secret}")
    private String jwtSecret;

    // Generates a token on successful authentication by the user
    // using username, issue date of token and the expiration date of the token.
    public String generateJwtToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts
                .builder()
                .setClaims(claims)  // set the claims
                .setSubject(userDetails.getUsername())  // set the username as subject in payload
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(getKey(), SignatureAlgorithm.HS256)  // signature part
                .compact();
    }

    // Validates the token
    // Checks if user is an authenticate one and using the token is the one that was generated and sent to the user.
    // Token is parsed for the claims such as username, roles, authorities, validity period etc.
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        final Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
        boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername())) && !isTokenExpired;
    }

    // get the username by checking subject of JWT Token
    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    // create a signing key based on secret
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
