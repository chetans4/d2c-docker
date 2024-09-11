package com.d2c.template.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {

  public InvalidTokenException(String msg) {
    super(msg);
  }

  public InvalidTokenException(Throwable th) {
    super(th.getMessage(), th);
  }

  public InvalidTokenException(String message, Throwable th) {
    super(message, th);
  }
}
