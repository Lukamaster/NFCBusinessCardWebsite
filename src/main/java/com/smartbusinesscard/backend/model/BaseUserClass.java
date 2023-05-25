package com.smartbusinesscard.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseUserClass {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Column(unique=true)
    protected String firstName;

    protected String username;

    protected String lastName;

    @Column(unique=true)
    protected String email;

    protected Date dateOfBirth;

    protected String countryOfResidence;

    protected String password;
}
