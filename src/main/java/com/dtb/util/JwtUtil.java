
package com.dtb.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {


        //private static final String SECRET_KEY_STRING =
       //     System.getenv("JWT_SECRET_KEY");
    private static final long EXPIRATION_TIME = 86400000L; // 24 hours in milliseconds
    private static final String SECRET_KEY = "VmpkR1ZqZzNkR2xrTmpnMk5qWTJNalUzTURrek1UazJNVFU1TlRrek1UVTJNVFU1TlRrM05UVT0="; // should ideally come from environment variables




    public String generateToken(String email) throws JwtException {
        try {
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() +
                            TimeUnit.HOURS.toMillis(EXPIRATION_TIME)))
                    .signWith( SignatureAlgorithm.HS512, key)
                    .compact();
        } catch (SecurityException | MalformedJwtException e) {
            throw new JwtException("Token creation failed: " + e.getMessage(), e);
        }
    }


    /**
     * Validate a token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException |
                 UnsupportedJwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Extract the email (subject) from a token
     */
    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
