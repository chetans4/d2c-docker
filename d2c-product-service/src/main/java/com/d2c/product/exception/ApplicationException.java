package com.d2c.product.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicationException extends RuntimeException {

  public ApplicationException(String message) {
    super(message);
  }

  public ApplicationException(Throwable cause) {
    super(cause);
  }

  public ApplicationException(String message, Throwable cause) {
    super(message, cause);
  }
}
