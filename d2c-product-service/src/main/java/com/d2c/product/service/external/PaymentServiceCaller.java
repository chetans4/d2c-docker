package com.d2c.product.service.external;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class PaymentServiceCaller {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Circuit Breaker with fallback and open state verification;
     * @param baseUrl
     * @param path
     * @return
     */
    @CircuitBreaker(name = "paymentGetCallCB", fallbackMethod = "fallbackPaymentGetCB")
    public Map getAPITrigger(String baseUrl, String path) {
        log.info("triggering payment service get api");
        Map response = restTemplate.getForObject(baseUrl+path, Map.class);
        log.info("Received response from payment get call : {}", response);
        return response;
    }

    public Map fallbackPaymentGetCB(String baseUrl, String path, Exception exception) {
        log.error("Fallback for Payment Get Request.", exception);
        Map fallbackResponse = new HashMap();
        fallbackResponse.put("status", "Payment Service is not available");
        return fallbackResponse;
    }

}
