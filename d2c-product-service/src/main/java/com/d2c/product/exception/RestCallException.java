package com.d2c.product.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Slf4j
public class RestCallException extends ApplicationException{

  private Map status;

  public RestCallException(Map status) {
    this.status = status;
  }

  public RestCallException(String message) {
    super(message);
  }
}
