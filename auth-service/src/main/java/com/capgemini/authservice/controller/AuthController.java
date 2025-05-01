package com.capgemini.authservice.controller;

import com.capgemini.authservice.dto.UserInfoDto;
import com.capgemini.authservice.entities.RefreshToken;
import com.capgemini.authservice.request.AuthRequestDto;
import com.capgemini.authservice.request.RefreshTokenRequestDto;
import com.capgemini.authservice.response.JwtResponseDto;
import com.capgemini.authservice.service.JwtService;
import com.capgemini.authservice.service.RefreshTokenService;
import com.capgemini.authservice.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@AllArgsConstructor
@RestController
public class AuthController
{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private AuthenticationManager authenticationManager;

    // AuthController.java
    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto) {
        try {
            Boolean isSignUped = userDetailsService.signupUser(userInfoDto);
            if (Boolean.FALSE.equals(isSignUped)) {
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }

            // Crucial: Load UserDetails after signup
            UserDetails userDetails = userDetailsService.loadUserByUsername(userInfoDto.getUsername());


            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.generateToken(userDetails); // Pass UserDetails here
            return new ResponseEntity<>(JwtResponseDto.builder().accessToken(jwtToken).
                    token(refreshToken.getToken()).build(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("auth/v1/login")
    public ResponseEntity<?> AuthenticateAndGetToken(@RequestBody AuthRequestDto authRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
            );

            if (authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String accessToken = jwtService.generateToken(userDetails);
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());

                // Create a custom response object to include more details
                JwtResponseDto response = new JwtResponseDto(accessToken, refreshToken.getToken(), userDetails.getUsername(), "Login successful!");  // Include username and message

                return ResponseEntity.ok(response); // Return the custom response
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }


//    @PostMapping("auth/v1/refreshToken")
//    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO) {
//        Logger logger = LoggerFactory.getLogger(AuthController.class); // Or inject a logger
//        logger.info("Received refresh token: {}", refreshTokenRequestDTO.getToken());
//        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getUserInfo)
//                .map(userInfo -> {
//                    // Load UserDetails using the username from userInfo
//                    UserDetails userDetails = userDetailsService.loadUserByUsername(userInfo.getUsername());
//                    String accessToken = jwtService.generateToken(userDetails); // Use UserDetails here
//                    return JwtResponseDto.builder()
//                            .accessToken(accessToken)
//                            .token(refreshTokenRequestDTO.getToken()).build();
//                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
//    }



    @PostMapping("auth/v1/refreshToken")
    public ResponseEntity<JwtResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO) {
        Optional<RefreshToken> refreshTokenOptional = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken());

        if (refreshTokenOptional.isPresent()) {
            RefreshToken refreshToken = refreshTokenOptional.get();

            refreshTokenService.verifyExpiration(refreshToken); // Verify refresh token hasn't expired

            String username = refreshToken.getUserInfo().getUsername(); // Assuming you store username with the refresh token
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Load user details
            String accessToken = jwtService.generateToken(userDetails); // Generate a new access token

            return ResponseEntity.ok(JwtResponseDto.builder().accessToken(accessToken).token(refreshToken.getToken()).build());

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Refresh token not found in DB");
        }
    }


@GetMapping("/check")
    public String check() {
        return "Working";
    }


}