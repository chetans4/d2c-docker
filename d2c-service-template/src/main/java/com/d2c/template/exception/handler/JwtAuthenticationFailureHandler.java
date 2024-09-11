package com.d2c.template.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.d2c.template.dto.response.ResponseBodyWrapper;
import com.d2c.template.dto.response.StatusResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * Invoked after an asynchronous login request by user fails. Responsible for serializing the
 * authentication failure reason to response.
 */
@Component
@Slf4j
public class JwtAuthenticationFailureHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException exception) throws IOException {

    log.error("Handling Access Denied : " + exception, exception);
    this.writeResponse(response, exception);
  }

  public void handleAuthFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException {
    log.error("Either endpoint url or token has issues.", exception);
    this.writeResponse(response, exception);
  }

  private static void writeResponse(HttpServletResponse response, RuntimeException exception)
      throws IOException {

    if (response.isCommitted()) {
      log.info("Did not write to response since already committed");
      return;
    }

    ObjectMapper objectMapper = new ObjectMapper();

    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    var status = StatusResponseDto.errorStatus(HttpStatus.NOT_ACCEPTABLE.value(),
        "Check endpoint url and token. " + exception.getMessage());
    var responseBodyWrapper = new ResponseBodyWrapper<>(status);

    objectMapper.writeValue(response.getWriter(), responseBodyWrapper);
  }

}

