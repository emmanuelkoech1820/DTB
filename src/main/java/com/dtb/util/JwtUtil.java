
package com.dtb.util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    //private static final long EXPIRATION_TIME = 86400000L;
    //private static final String SECRET_KEY = "VmpkR1ZqZzNkR2xrTmpnMk5qWTJNalUzTURrek1UazJNVFU1TlRrek1UVTJNVFU1TlRrM05UVT0=";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public  String generateToken(String subject) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

}


    /**
     * Validate a token
     */
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser()
//                    .setSigningKey(SECRET_KEY)
//                    .parseClaimsJws(token);
//            return true;
//        } catch (ExpiredJwtException | SignatureException | MalformedJwtException |
//                 UnsupportedJwtException | IllegalArgumentException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    /**
     * Extract the email (subject) from a token
     */
//    public String extractEmail(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody();
//        return claims.getSubject();
//    }

