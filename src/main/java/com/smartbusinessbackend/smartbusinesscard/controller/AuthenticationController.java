package com.smartbusinessbackend.smartbusinesscard.controller;

import com.smartbusinessbackend.smartbusinesscard.model.dto.AuthenticationRequest;
import com.smartbusinessbackend.smartbusinesscard.model.dto.AuthenticationResponse;
import com.smartbusinessbackend.smartbusinesscard.model.dto.RegisterRequest;
import com.smartbusinessbackend.smartbusinesscard.service.impl.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok().body(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok().body(authenticationService.authenticate(request));

    }
}
