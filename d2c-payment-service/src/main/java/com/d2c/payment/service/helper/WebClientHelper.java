package com.d2c.payment.service.helper;

import com.d2c.payment.configuration.D2CURIBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Map;

@Slf4j
@Component
public class WebClientHelper {

    @Autowired
    private WebClient webClient;

    public JsonNode triggerGetRequest(String baseUrl, String path,
                                      MultiValueMap<String, String> paramMap, Map<String, String> headers) {
        String uri = null;
        if(Strings.isNotBlank(path)){
            uri = D2CURIBuilder
                    .fromHttpUrl(baseUrl+path).queryParams(paramMap).buildInString();
        }else{
            uri = D2CURIBuilder
                    .fromHttpUrl(baseUrl).queryParams(paramMap).buildInString();
        }
        var requestHeadersSpec = webClient.get()
                .uri(uri)
                .headers(httpHeaders -> {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        httpHeaders.add(entry.getKey(), entry.getValue());
                    }
                });
        return requestHeadersSpec.retrieve().bodyToMono(JsonNode.class).block();
    }

    public JsonNode triggerPostRequest(
            String baseUrl,
            String path,
            MultiValueMap<String, String> paramMap,
            Map<String, String> headers,
            String requestDto) {
        URI uri = null;
        if(Strings.isNotBlank(path)){
            uri = D2CURIBuilder
                    .fromHttpUrl(baseUrl+path).queryParams(paramMap).build();
        }
        else {
            uri = D2CURIBuilder
                    .fromHttpUrl(baseUrl).queryParams(paramMap).build();
        }
        return webClient
                .post()
                .uri(uri)
                .headers(
                        httpHeaders -> {
                            for (Map.Entry<String, String> entry : headers.entrySet()) {
                                httpHeaders.add(entry.getKey(), entry.getValue());
                            }
                        })
                .body(Mono.just(requestDto), String.class)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
    }

}
