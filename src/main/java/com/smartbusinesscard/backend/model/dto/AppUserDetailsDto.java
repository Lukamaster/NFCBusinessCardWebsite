package com.smartbusinesscard.backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AppUserDetailsDto {

    private String firstName;
    private String username;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String countryOfResidence;
}
