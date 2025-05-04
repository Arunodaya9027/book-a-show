package com.capgemini.authservice.service.impl;

import com.capgemini.authservice.dto.ChangePasswordDto;
import com.capgemini.authservice.dto.UserInfoDto;
import com.capgemini.authservice.entities.UserInfo;
import com.capgemini.authservice.repository.UserRepository;
import com.capgemini.authservice.service.IPasswordResetService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordResetServiceImpl implements IPasswordResetService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean changePassword(ChangePasswordDto changePasswordDto , String username) {
        UserInfo user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(changePasswordDto.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;


    }
}
