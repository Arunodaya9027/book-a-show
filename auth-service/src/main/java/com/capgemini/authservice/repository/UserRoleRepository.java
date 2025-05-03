package com.capgemini.authservice.repository;

import com.capgemini.authservice.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRoleName(UserRole.RoleName roleName); // To find roles by name
}
