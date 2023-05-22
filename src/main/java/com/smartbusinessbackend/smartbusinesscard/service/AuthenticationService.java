package com.smartbusinessbackend.smartbusinesscard.service;

import com.smartbusinessbackend.smartbusinesscard.model.dto.AuthenticationRequest;
import com.smartbusinessbackend.smartbusinesscard.model.dto.AuthenticationResponse;
import com.smartbusinessbackend.smartbusinesscard.model.dto.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
