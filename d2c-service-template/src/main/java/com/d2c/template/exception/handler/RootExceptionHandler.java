package com.d2c.template.exception.handler;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.d2c.template.dto.response.ResponseBodyWrapper;
import com.d2c.template.dto.response.StatusResponseDto;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handler for Exceptions and system exceptions.
 */
@RestControllerAdvice
@Slf4j
public class RootExceptionHandler {

  /**
   * This Handler will apply when no other handler applicable.
   *
   * @param ex
   * @return
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseBodyWrapper<ObjectNode> handleAllException(Exception ex) {
    var status = StatusResponseDto.errorStatus(
        HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    return new ResponseBodyWrapper<>(status);
  }

  @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class,
      ConstraintViolationException.class, DataIntegrityViolationException.class})
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ResponseBodyWrapper<ObjectNode> handleSQLException(Exception ex) {
    var status = StatusResponseDto.errorStatus(HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase());
    return new ResponseBodyWrapper<>(status);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseBodyWrapper<ObjectNode> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    HashMap<String, String> errors = this.generateFieldValidationResponse(ex);
    var status = StatusResponseDto.errorStatus(HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
    return new ResponseBodyWrapper<>(status);
  }

  private HashMap<String, String> generateFieldValidationResponse(
      MethodArgumentNotValidException ex) {
    HashMap<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

}
