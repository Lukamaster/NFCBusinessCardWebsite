package com.smartbusinessbackend.smartbusinesscard.service.impl;

import com.smartbusinessbackend.smartbusinesscard.model.AppUser;
import com.smartbusinessbackend.smartbusinesscard.model.Role;
import com.smartbusinessbackend.smartbusinesscard.model.dto.RegisterRequest;
import com.smartbusinessbackend.smartbusinesscard.repository.UserRepository;
import com.smartbusinessbackend.smartbusinesscard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser findByEmail(String email) {
        Optional<AppUser> user = userRepository.findByEmail(email);
        if (user.isPresent())
            return user.get();
        else{
            log.error("User not found with provided email: '{}'",email);
            throw new IllegalArgumentException();
        }
    }

    @Override
    public AppUser saveUser(RegisterRequest request) {
        AppUser appUser = AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .dateOfBirth(request.getDateOfBirth())
                .countryOfResidence(request.getCountryOfResidence())
                .role(Role.USER)
                .build();
        try {
           return userRepository.save(appUser);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email.isEmpty()){
            log.error("Email is empty, cannot find any user");
            return null;
        }
        return findByEmail(email);
    }
}
