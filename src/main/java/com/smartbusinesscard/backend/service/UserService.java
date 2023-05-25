package com.smartbusinesscard.backend.service;

import com.smartbusinesscard.backend.model.AppUser;
import com.smartbusinesscard.backend.model.dto.AppUserDetailsDto;
import com.smartbusinesscard.backend.model.dto.RegisterRequest;

import java.util.List;


public interface UserService {

    AppUser findByEmail(String email);
    AppUser saveUser(RegisterRequest request);
    List<AppUser> getAllRegisteredUsers();
    AppUserDetailsDto getUserById(String id);
}
