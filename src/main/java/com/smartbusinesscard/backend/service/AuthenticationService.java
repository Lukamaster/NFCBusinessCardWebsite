package com.smartbusinesscard.backend.service;

import com.smartbusinesscard.backend.model.dto.AuthenticationRequest;
import com.smartbusinesscard.backend.model.dto.AuthenticationResponse;
import com.smartbusinesscard.backend.model.dto.RegisterRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
