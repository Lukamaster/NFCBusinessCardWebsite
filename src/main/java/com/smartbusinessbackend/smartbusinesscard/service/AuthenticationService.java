package com.smartbusinessbackend.smartbusinesscard.service;

import com.smartbusinessbackend.smartbusinesscard.model.AuthenticationRequest;
import com.smartbusinessbackend.smartbusinesscard.model.AuthenticationResponse;
import com.smartbusinessbackend.smartbusinesscard.model.RegisterRequest;
import com.smartbusinessbackend.smartbusinesscard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
