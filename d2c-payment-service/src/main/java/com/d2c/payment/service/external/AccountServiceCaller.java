package com.d2c.payment.service.external;

import com.d2c.payment.service.helper.WebClientHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AccountServiceCaller {

    @Autowired
    private WebClientHelper webClientHelper;

    @CircuitBreaker(name = "accountGetCallCB", fallbackMethod = "fallbackGetCB")
    @Retry(name = "getCallRetry")
    public JsonNode getAPITrigger(String baseUrl, String path,
                                 MultiValueMap<String, String> paramMap) {

        Map<String, String> header = new HashMap<>();
        log.info("calling account service get.");
        JsonNode jsonNode = webClientHelper.triggerGetRequest(baseUrl, path, paramMap, header);
        log.info("Received Response from account Service: {}", jsonNode);
        return jsonNode;
    }

    public JsonNode fallbackGetCB(
            String url,
            String path,
            MultiValueMap<String, String> paramMap,
            WebClientRequestException exception) {
        log.error("Fallback for Get Request WebClientRequestException.", exception);
//        throw new ApplicationException(
//                "Fallback for Get Request WebClientRequestException.", exception);
        ObjectNode fallbackResponse = new ObjectNode(null);
        fallbackResponse.put("status", "Account Service is not available");
        return fallbackResponse;
    }

}
