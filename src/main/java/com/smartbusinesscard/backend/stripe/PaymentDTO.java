package com.smartbusinesscard.backend.stripe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDTO {

    private String customerFullName;
    private String description;
    private Integer amount;
    private Currency currency;
    private String email;
    private String token;
}
