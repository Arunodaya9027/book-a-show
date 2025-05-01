package com.capgemini.authservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    @Id
    @Column(name = "role_id")
    private Long roleId;


    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public enum RoleName {
        USER, OWNER, ADMIN
    }

}
