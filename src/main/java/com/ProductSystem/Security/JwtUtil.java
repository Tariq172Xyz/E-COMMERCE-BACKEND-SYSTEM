package com.ProductSystem.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {


    @Value("${jwt.secret}")
    private String secretKey;



    //generating a secret key ,which will be used to generate/verify token
    public SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails){
       return Jwts.builder().subject(userDetails.getUsername()).
               issuedAt(new Date()).expiration(new Date()).
               signWith(getSigningKey()).
               compact();
    }

    //return the claims such as name/role/expiry
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //get name from token. why: sometimes we only need to check name instead of all claims
    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    //checks token expiration
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    //checks for token validation
    //why -> whether the token is forged by
    // someone or not such as changed name or something which changes base64
    public boolean isTokenValid(String token,UserDetails userDetails){
        String username=extractUsername(userDetails.getUsername());
        return username.equals(userDetails.getUsername())&& !isTokenExpired(token);
    }


}
