package com.capgemini.authservice.dto;



import com.capgemini.authservice.entities.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDto  extends UserInfo {

    private String userName;

    private String lastName;
    private long phoneNumber;
    private String email;


}
