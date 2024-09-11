package com.d2c.template.util;

import lombok.Getter;

public interface ResponseMessageCodes {

  @Getter
  public enum Internal {
    SUCCESS(200, "Success."),
    FAILED(400, "Failed."),
    INVALID_DATA(202, "Invalid data found."),
    SERVICE_UNAVAILABLE(503, "SERVICE UNAVAILABLE");
    private Integer code;
    private String message;

    private Internal(Integer code, String message) {
      this.code = code;
      this.message = message;
    }


  }

  @Getter
  public enum External {
    SUCCESS(201, "Success."),
    FAILED(401, "Failed."),
    INVALID_SESSION(402, "Invalid Session."),
    LOGIN_FAILED(403, "Login Failure."),
    UNAUTHORIZED(401, "Unauthorized"),
    SERVER_ERROR(500, "Server Error.");
    private Integer code;
    private String message;

    private External(Integer code, String message) {
      this.code = code;
      this.message = message;
    }
  }
}
