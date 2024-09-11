package com.d2c.template.exception;

import lombok.NoArgsConstructor;

/**
 * Root exception for custom exceptions, Use this if different handling approach not required.
 * Developers can create more root exceptions, extending RuntimeException, if required to provide
 * error details or handle in a different way.
 */
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