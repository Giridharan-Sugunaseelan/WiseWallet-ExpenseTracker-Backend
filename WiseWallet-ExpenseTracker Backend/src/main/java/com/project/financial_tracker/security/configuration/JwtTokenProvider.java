package com.project.financial_tracker.security.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
//
//    @Value("${app.jwt.secret}")
//    private String secretKey;
//    @Value("${app.jwt.expirationTime}")
//    private long expirationTime;
//
//    Date currentDate = new Date();
//    Date expiryDate = new Date(currentDate.getTime() + expirationTime);
//
//    public Key key(){
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
//    }
//
//    public String generateToken(Authentication authentication){
//        return Jwts.builder().subject(authentication.getName()).issuedAt(new Date()).expiration(expiryDate).signWith(key()).compact();
//    }
//
//    public String getUserNameFromToken(String token){
//        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
//    }

    // Secret key for signing the JWT token
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    // Expiration time of the JWT token in milliseconds
    @Value("${app.jwt.expirationTime}")
    private long jwtExpirationDate;
public String generateToken(Authentication authentication){
    // Extracting username from authentication
    String username = authentication.getName();
    Date currentDate = new Date();
    Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
    // Building JWT token
    String token = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .signWith(key())// Signing the token with HMAC
            .compact();
    return token;
    //the form expire date is ->
}
    // Utility method to get the username from a JWT token
    public String getUserNameFromToken(String token){
        // Parsing the JWT token and extracting claims
        Claims body = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        // Extracting the username from the claims
        return body.getSubject();
    }
    // Utility method to validate JWT token
    public boolean validateToken(String token){
        // Parsing and validating the JWT token
        Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parse(token);
        // If parsing and validation are successful, return true
        return true;
    }
    // Utility method to get the key used for signing
    private Key key() {
        // Decoding the secret key from Base64 and returning it
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
