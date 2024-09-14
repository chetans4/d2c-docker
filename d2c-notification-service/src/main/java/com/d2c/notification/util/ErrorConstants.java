package com.d2c.notification.util;

public enum ErrorConstants {
	
	REQ_ERROR_CODE(500, "Error occurred in processing"),
	APPLICATION_ERROR(501, "Some error occurred while processing your request."),
	CLIENT_SIDE_ERROR(401, "Client side error occurred"),
	INVALID_ERROR(402, "Validation has failed"),
	DATA_INTEGRITY_ERROR(403, "Data integrity error occurred"),
	UNAUTHORISED_ERROR(404, "Unauthorised user error occurred"),
	SERVER_SIDE_ERROR(502, "Server side error occurred"),
	METHOD_NOT_ALLOWED(405,"Request method '%s' not supported");

	private final Integer errorCode;
	private final String errorMessage;
	
	private ErrorConstants(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	
}
