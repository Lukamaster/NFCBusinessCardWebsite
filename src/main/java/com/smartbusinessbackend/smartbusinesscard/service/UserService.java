package com.smartbusinessbackend.smartbusinesscard.service;

import com.smartbusinessbackend.smartbusinesscard.model.AppUser;
import com.smartbusinessbackend.smartbusinesscard.model.dto.RegisterRequest;


public interface UserService {

    AppUser findByEmail(String email);
    AppUser saveUser(RegisterRequest request);
}
