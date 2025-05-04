package com.capgemini.authservice.controller;


import com.capgemini.authservice.dto.ChangePasswordDto;
import com.capgemini.authservice.service.IPasswordResetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/v1")
@AllArgsConstructor
public class PasswordController {

    private final IPasswordResetService passwordResetService;

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get logged-in username

        if (passwordResetService.changePassword(changePasswordDto, username)) {
            return ResponseEntity.ok("Password changed successfully!");
        } else {
            return ResponseEntity.badRequest().body("Failed to change password. Please check your current password.");
        }
    }
}
