package com.capgemini.authservice.service;




import com.capgemini.authservice.dto.UserInfoDto;
import com.capgemini.authservice.entities.UserInfo;
import com.capgemini.authservice.entities.UserRole;
import com.capgemini.authservice.repository.UserRepository;
import com.capgemini.authservice.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Component
@AllArgsConstructor
@Data
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    private final UserRoleRepository userRoleRepository;





    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);
        if(user == null){

            throw new UsernameNotFoundException("could not found user..!!");
        }

        return new CustomUserDetails(user);
    }

    public UserInfo checkIfUserAlreadyExist(UserInfoDto userInfoDto){
        return userRepository.findByUsername(userInfoDto.getUsername());
    }

    public Boolean signupUser(UserInfoDto userInfoDto) {
        //        ValidationUtil.validateUserAttributes(userInfoDto);
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if (Objects.nonNull(checkIfUserAlreadyExist(userInfoDto))) {
            return false;
        }
        String userId = UUID.randomUUID().toString();
        // Assign default role USER
//        UserRole defaultRole = userRoleRepository.findByRoleName(UserRole.RoleName.USER);
//        Set<UserRole> roles = new HashSet<>();
//        roles.add(defaultRole);


        Set<UserRole> roles = new HashSet<>();

        if (userInfoDto.getRoles() == null || userInfoDto.getRoles().isEmpty()) {
            // default role
            roles.add(userRoleRepository.findByRoleName(UserRole.RoleName.USER));
        } else {
            for (String role : userInfoDto.getRoles()) {
                UserRole.RoleName roleName = UserRole.RoleName.valueOf(role.toUpperCase());
                UserRole foundRole = userRoleRepository.findByRoleName(roleName);
                if (foundRole != null) {
                    roles.add(foundRole);
                }
            }
        }

            userRepository.save(new UserInfo(userId, userInfoDto.getUsername(), userInfoDto.getPassword(), userInfoDto.getEmail(), roles));


            return true;
        }

    }