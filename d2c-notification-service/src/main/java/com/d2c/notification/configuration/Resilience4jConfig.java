package com.d2c.notification.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerEventListener circuitBreakerEventListener(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreakerEventListener listener = new CircuitBreakerEventListener();
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(cb ->
                cb.getEventPublisher().onEvent(listener::onCircuitBreakerEvent)
        );
        return listener;
    }
}
