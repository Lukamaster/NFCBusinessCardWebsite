package com.smartbusinessbackend.smartbusinesscard.service;

import com.smartbusinessbackend.smartbusinesscard.model.AppUser;
import com.smartbusinessbackend.smartbusinesscard.model.dto.AppUserDetailsDto;
import com.smartbusinessbackend.smartbusinesscard.model.dto.RegisterRequest;

import java.util.List;


public interface UserService {

    AppUser findByEmail(String email);
    AppUser saveUser(RegisterRequest request);
    List<AppUser> getAllRegisteredUsers();
    AppUserDetailsDto getUserById(Long id);
}
