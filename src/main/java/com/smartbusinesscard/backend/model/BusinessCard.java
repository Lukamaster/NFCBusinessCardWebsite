package com.smartbusinesscard.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessCard {

    //TODO: modify this , send appropriate data accordingly
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String generatedUrl;
}
