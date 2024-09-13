package com.d2c.payment.configuration;

import io.github.resilience4j.circuitbreaker.event.CircuitBreakerEvent;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnErrorEvent;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CircuitBreakerEventListener {

    public void onCircuitBreakerEvent(CircuitBreakerEvent event) {
        if (event instanceof CircuitBreakerOnErrorEvent) {
            CircuitBreakerOnErrorEvent errorEvent = (CircuitBreakerOnErrorEvent) event;
            log.error("Circuit breaker error: {}", errorEvent.getThrowable().getMessage());
        } else if (event instanceof CircuitBreakerOnStateTransitionEvent) {
            CircuitBreakerOnStateTransitionEvent transitionEvent = (CircuitBreakerOnStateTransitionEvent) event;
            log.info("Circuit breaker state transition: {}", transitionEvent.getStateTransition());
            //Here can plan any email alert
        }
    }
}
