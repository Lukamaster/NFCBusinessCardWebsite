package com.smartbusinesscard.backend.config.rabbitmq;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:rabbitmq-config.properties")
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class RabbitMQConfigParams {

    @NotEmpty
    private String host;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotNull
    private Integer port;

    @NotEmpty
    private String queue;

    @NotEmpty
    private String exchange;

    @NotEmpty
    private String routingKey;
}
