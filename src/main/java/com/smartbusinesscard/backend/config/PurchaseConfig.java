package com.smartbusinesscard.backend.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
@ConfigurationProperties(prefix = "purchase")
@Getter
@Setter
public class PurchaseConfig {

    @NotEmpty
    private String secretApiKey;

    @NotEmpty
    private String publishableKey;

    @NotEmpty
    private String baseUrl;

    @NotNull
    private Long creditCardNumber;

    @NotNull
    private Integer cvc;

    @NotEmpty
    private String brand;

    @NotEmpty
    private String cardToken;

}
