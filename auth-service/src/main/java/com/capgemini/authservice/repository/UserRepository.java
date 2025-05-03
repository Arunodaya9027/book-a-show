package com.capgemini.authservice.repository;

import com.capgemini.authservice.entities.UserInfo;
import com.capgemini.authservice.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    public UserInfo findByUsername(String username);


}
