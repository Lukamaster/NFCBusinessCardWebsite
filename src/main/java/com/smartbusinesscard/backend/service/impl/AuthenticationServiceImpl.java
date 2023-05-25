package com.smartbusinesscard.backend.service.impl;

import com.smartbusinesscard.backend.model.dto.AuthenticationRequest;
import com.smartbusinesscard.backend.model.dto.AuthenticationResponse;
import com.smartbusinesscard.backend.model.dto.RegisterRequest;
import com.smartbusinesscard.backend.service.AuthenticationService;
import com.smartbusinesscard.backend.service.UserService;
import com.smartbusinesscard.backend.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        AppUser appUser = userService.saveUser(request);
        String jwtToken = jwtService.generateToken(appUser);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
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