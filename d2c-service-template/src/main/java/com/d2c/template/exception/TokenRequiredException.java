package com.d2c.template.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Custom exception or handling assess denied scenarios
 */
public class TokenRequiredException extends AuthenticationException {

  public TokenRequiredException(String msg) {
    super(msg);
  }

  public TokenRequiredException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
