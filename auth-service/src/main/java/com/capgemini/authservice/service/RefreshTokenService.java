package com.capgemini.authservice.service;





import com.capgemini.authservice.entities.RefreshToken;
import com.capgemini.authservice.entities.UserInfo;
import com.capgemini.authservice.repository.RefreshTokenRepository;
import com.capgemini.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {


    RefreshTokenRepository refreshTokenRepository;

    private static final Logger log = LoggerFactory.getLogger(RefreshTokenService.class);

    UserRepository userRepository;

//    public RefreshToken createRefreshToken(String username){
//        UserInfo userInfoExtracted = userRepository.findByUsername(username);
//        RefreshToken refreshToken = RefreshToken.builder()
//                .userInfo(userInfoExtracted)
//                .token(UUID.randomUUID().toString())
//                .expiryDate(Instant.now().plusMillis(600000))
//                .build();
//        return refreshTokenRepository.save(refreshToken);
//    }

    public RefreshToken getOrCreateRefreshToken(String username) {
        UserInfo userInfo = userRepository.findByUsername(username);

        return refreshTokenRepository.findByUserInfo(userInfo)
                .map(existingToken -> {
                    existingToken.setToken(UUID.randomUUID().toString()); // Generate new token value
                    existingToken.setExpiryDate(Instant.now().plusMillis(600000)); // Update expiry date
                    return refreshTokenRepository.save(existingToken); // Save updated token
                })
                .orElseGet(() -> {  // Create a new token if none exists
                    RefreshToken refreshToken = RefreshToken.builder()
                            .userInfo(userInfo)
                            .token(UUID.randomUUID().toString())
                            .expiryDate(Instant.now().plusMillis(600000))
                            .build();
                    return refreshTokenRepository.save(refreshToken);
                });
    }

//    public Optional<RefreshToken> findByToken(String token){
//        return refreshTokenRepository.findByToken(token);
//    }

    // In RefreshTokenService.java
    public Optional<RefreshToken> findByToken(String token) {
        log.info("Searching for refresh token: {}", token); // Log the token being searched

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token);

        if (refreshToken.isPresent()) {
            log.info("Found refresh token: {}", refreshToken.get()); // Log the found token
        } else {
            log.warn("Refresh token not found: {}", token); // Log if not found
        }

        return refreshToken;
    }


    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }




}