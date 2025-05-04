package com.capgemini.authservice.dto;



import com.capgemini.authservice.entities.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Set;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDto   {

    private String username;

    private String lastName;
    private long phoneNumber;
    private String email;
    private String password;
    private Set<String> roles;




}
