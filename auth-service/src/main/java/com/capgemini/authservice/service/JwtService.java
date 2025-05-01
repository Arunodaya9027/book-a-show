//package com.capgemini.authservice.service;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class JwtService {
//
//    // Base64-encoded 256-bit secret (for example purposes)
//    private static final String SECRET_KEY = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";
//
//    // Decode the secret and build an HMAC-SHA key
//    private SecretKey getSigningKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes); // HS256 key (requires >=256-bit)&#8203;:contentReference[oaicite:11]{index=11}&#8203;:contentReference[oaicite:12]{index=12}
//    }
//
//    // 1. Generate JWT with username and roles
//    public String generateToken(UserDetails userDetails) {
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//
//        return Jwts.builder()
//                .subject(userDetails.getUsername())
//                .claim("roles", roles)                // include user roles as a claim&#8203;:contentReference[oaicite:13]{index=13}
//                .issuedAt(new Date())
//                .expiration(new Date(System.currentTimeMillis() + 3600000)) // e.g. 1 hour
//                .signWith(getSigningKey(),SignatureAlgorithm.HS256) // sign with HS256&#8203;:contentReference[oaicite:14]{index=14}
//                .compact();
//    }
//
//    // 2. Extract username from JWT
//    public String extractUsername(String token) {
//        return extractAllClaims(token).getSubject();
//    }
//
//    // 3. Extract roles from JWT claims



////    @SuppressWarnings("unchecked")
//    public List<String> extractRoles(String token) {
//        Claims claims = extractAllClaims(token);
//        Object roles = claims.get("roles");
//        if (roles instanceof List<?>) {
//            return ((List<?>) roles).stream()
//                    .map(Object::toString)
//                    .toList();
//        }
//        return List.of();
//    }
//
//    // Helper: parse and validate JWT, returning all claims
//    private Claims extractAllClaims(String token) {
//        Jws<Claims> jwt = Jwts.parser()
//                .verifyWith(getSigningKey())
//                .build()
//                .parseSignedClaims(token); // validates signature with the key&#8203;:contentReference[oaicite:15]{index=15}
//
//        return jwt.getPayload();
//    }
//
//    // Example: validate token against user details (checks subject and expiration)
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        final Date expiration = extractAllClaims(token).getExpiration();
//        return (username.equals(userDetails.getUsername()) && expiration.after(new Date()));
//    }
//}




//package com.capgemini.authservice.service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//
//
//@Service
//public class JwtService {
//
//    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";
//
//    private Key getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public List<String> extractRoles(String token) {
//        Claims claims = extractAllClaims(token);
//        Object roles = claims.get("roles");
//        if (roles instanceof List<?>) {
//            return ((List<?>) roles).stream().map(Object::toString).toList();
//        }
//        return List.of();
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts
//                .parser()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//
//    private Boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    public String generateToken(UserDetails userDetails) {
//        Map<String, Object> claims = new HashMap<>();
//
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .toList();
//
//        claims.put("roles", roles);
//        return createToken(claims, userDetails.getUsername());
//    }
//
//    private String createToken(Map<String, Object> claims, String username) {
//        return Jwts.builder()
//                .claims(claims)
//                .subject(username)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//}
//




package com.capgemini.authservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Secret key used for signing the JWTs (should ideally be stored securely)
    public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629";

    // Generate signing key from the secret using HS256 algorithm
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract username (subject) from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract roles claim as list of strings from the token
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        Object roles = claims.get("roles");
        if (roles instanceof List<?>) {
            return ((List<?>) roles).stream().map(Object::toString).toList();
        }
        return List.of();
    }

    // Extract any custom claim from the token using a resolver function
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Parse all claims from the JWT
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Check whether the token has expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate the token by checking username match and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Generate a JWT token with roles included in claims
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        // Extract roles from user details and add to claims
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        claims.put("roles", roles);
        return createToken(claims, userDetails.getUsername());
    }

    // Build and sign the JWT token with the provided claims and username
    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // 10 minutes expiry
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
