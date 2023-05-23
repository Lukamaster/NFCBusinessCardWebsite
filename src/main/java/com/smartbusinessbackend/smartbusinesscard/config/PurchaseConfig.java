package com.smartbusinessbackend.smartbusinesscard.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
}
