package com.d2c.notification.dto.resp;

import com.d2c.notification.util.JsonUtil;
import lombok.ToString;

import java.util.List;

public class ErrorWrapper {

	private boolean hasErrors;
	private List<ErrorDetailsWrapper> errorDetails;

	public ErrorWrapper() {
		super();
	}

	public ErrorWrapper(boolean hasErrors, List<ErrorDetailsWrapper> errorDetails) {
		super();
		this.hasErrors = hasErrors;
		this.errorDetails = errorDetails;
	}

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public List<ErrorDetailsWrapper> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<ErrorDetailsWrapper> errorDetails) {
		this.errorDetails = errorDetails;
	}

	@Override
	public String toString() {
		return JsonUtil.objectToJson(this, false);
	}

}
