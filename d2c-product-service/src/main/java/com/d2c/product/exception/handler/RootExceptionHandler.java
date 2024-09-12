package com.d2c.product.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RootExceptionHandler {

  /**
   * @param ex
   * @return
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public String handleAllException(Exception ex) throws Exception {
    log.error("Handling Exception", ex);
    throw ex;
  }

}
