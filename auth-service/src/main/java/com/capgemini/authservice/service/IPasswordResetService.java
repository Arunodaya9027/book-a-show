package com.capgemini.authservice.service;

import com.capgemini.authservice.dto.ChangePasswordDto;
public interface IPasswordResetService {

     boolean changePassword(ChangePasswordDto changePasswordDto,String username);


}
