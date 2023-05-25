package com.smartbusinesscard.backend.config.rabbitmq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConfig implements SmartInitializingSingleton {

    private final RabbitMQConfigParams rabbitMQConfigParams;

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitMQConfigParams.getHost());
        connectionFactory.setPort(rabbitMQConfigParams.getPort());
        connectionFactory.setUsername(rabbitMQConfigParams.getUsername());
        connectionFactory.setPassword(rabbitMQConfigParams.getPassword());
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(rabbitTemplate());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(cachingConnectionFactory());
    }

    @Bean
    public Queue queue() {
        return new Queue(rabbitMQConfigParams.getQueue(), true, false, false);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange(rabbitMQConfigParams.getExchange(), true, false);
    }

    @Bean
    public Binding binding() {
        return new Binding(queue().getName(), Binding.DestinationType.QUEUE, exchange().getName(), rabbitMQConfigParams.getRoutingKey(), new HashMap<>());
    }

    @Override
    public void afterSingletonsInstantiated() {
        rabbitAdmin().declareQueue(queue());
        rabbitAdmin().declareBinding(binding());
        rabbitAdmin().declareExchange(exchange());
    }
}
