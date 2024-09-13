package com.d2c.payment.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.util.Map;

@Getter
@Slf4j
public class WebClient4xxException extends ApplicationException{

  private Map status;
  private ClientResponse response;

  public WebClient4xxException(Map status) {
    this.status = status;
  }

  public WebClient4xxException(ClientResponse response) {
    super(response.toString());
    log.info("response : {}", response);
    this.response = response;
  }

  public WebClient4xxException(String message) {
    super(message);
  }
}
