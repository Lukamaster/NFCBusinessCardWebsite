package com.smartbusinessbackend.smartbusinesscard.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchasedCard {

    //TODO: modify this , send appropriate data accordingly
    private AppUser appUser;
    private String generatedUrl;
}
