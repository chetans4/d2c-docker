package com.d2c.template.exception.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.d2c.template.dto.response.ResponseBodyWrapper;
import com.d2c.template.dto.response.StatusResponseDto;
import com.d2c.template.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handler for ApplicationException Exceptions and Another custom exceptions.
 */
@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

  /**
   * Handler for user defined exceptions
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(value = ApplicationException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseBodyWrapper<ObjectNode> handleApplicationException(ApplicationException ex) {
    log.error("Handling ApplicationException.", ex);
    var status = StatusResponseDto.errorStatus(
        HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    return new ResponseBodyWrapper<>(status);
  }

}
