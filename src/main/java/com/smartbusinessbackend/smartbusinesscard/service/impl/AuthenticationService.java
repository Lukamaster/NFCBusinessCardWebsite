package com.smartbusinessbackend.smartbusinesscard.service.impl;

import com.smartbusinessbackend.smartbusinesscard.model.*;
import com.smartbusinessbackend.smartbusinesscard.model.dto.AuthenticationRequest;
import com.smartbusinessbackend.smartbusinesscard.model.dto.AuthenticationResponse;
import com.smartbusinessbackend.smartbusinesscard.model.dto.RegisterRequest;
import com.smartbusinessbackend.smartbusinesscard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationResponse register(RegisterRequest request) {
        AppUser appUser = userService.saveUser(request);
        String jwtToken = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        AppUser appUser = userService.findByEmail(request.getEmail());

        String jwtToken = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}