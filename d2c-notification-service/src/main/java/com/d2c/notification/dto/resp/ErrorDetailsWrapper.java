package com.d2c.notification.dto.resp;

import com.d2c.notification.util.JsonUtil;
import lombok.ToString;

public class ErrorDetailsWrapper {

	private String errorSourceId;
	private Integer errorCode;
	private String errorMessage;

	public ErrorDetailsWrapper() {
		super();
	}

	public ErrorDetailsWrapper(String errorSourceId, Integer errorCode, String errorMessage) {
		super();
		this.errorSourceId = errorSourceId;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorSourceId() {
		return errorSourceId;
	}

	public void setErrorSourceId(String errorSourceId) {
		this.errorSourceId = errorSourceId;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return JsonUtil.objectToJson(this, false);
	}

}
