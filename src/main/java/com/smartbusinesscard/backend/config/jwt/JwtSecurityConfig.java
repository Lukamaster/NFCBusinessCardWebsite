package com.smartbusinesscard.backend.config.jwt;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:jwt-auth.properties")
@ConfigurationProperties(prefix = "jwt.security")
@Setter
@Getter
public class JwtSecurityConfig {

    @NotNull
    private String secretKey;

    @NotNull
    private Long tokenExpiration;

    @NotNull
    private Long refreshTokenExpiration;

    @NotNull
    private Boolean enabled;

    @NotEmpty
    private String authorizeMode;
}
