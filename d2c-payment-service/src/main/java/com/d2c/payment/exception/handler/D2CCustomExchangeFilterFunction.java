package com.d2c.payment.exception.handler;


import com.d2c.payment.exception.ApplicationException;
import com.d2c.payment.exception.WebClient4xxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Component
public class D2CCustomExchangeFilterFunction implements ExchangeFilterFunction {

  @Override
  public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
    log.info("D2CCustomExchangeFilterFunction request {}", request);
    log.info("D2CCustomExchangeFilterFunction next : {}", next);
    return next.exchange(request)
        .flatMap(response -> {
          log.info("response code value : {}", response.statusCode().value());
          HttpStatusCode statusCode = response.statusCode();
          HttpStatus status = HttpStatus.valueOf(statusCode.value());

          if (statusCode.is5xxServerError()) {

            log.info("response status error : {}", response.statusCode());
            log.info("response error : {}", response);
            throw new ApplicationException("Filter 5xx error : " + status.getReasonPhrase());

            } else if (statusCode.is4xxClientError()) {

            log.info("response status error : {}", response.statusCode());
            Mono<Map> bodyMono = response.bodyToMono(Map.class);

            log.info("bodyMono : {}", bodyMono);
            if(HttpStatusCode.valueOf(404).equals(statusCode)){
              throw new WebClient4xxException(bodyMono.toString());
            }

            Disposable disposable = bodyMono.subscribe(responseBody -> {

              log.info("response error responseBody : {}", responseBody);
              throw new WebClient4xxException(String.valueOf(responseBody));
            });
          }
            log.info("just response : {}", response);
            return Mono.just(response);
        });
  }

}
