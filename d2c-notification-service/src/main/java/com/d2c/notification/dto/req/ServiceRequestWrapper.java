package com.d2c.notification.dto.req;

import java.util.HashMap;
import java.util.Map;

/**
 * Wrapper class for request to accept additional information or metadata with
 * core request data.
 * 
 * TODO: Request wrapping needs to be implemented.
 */

public class ServiceRequestWrapper {

	private String correlationId;
	private Map<String, String> headers = new HashMap<>();

	public ServiceRequestWrapper() {
	}

	public ServiceRequestWrapper(Map<String, String> headers) {
		if (headers != null && headers.size() > 0) {
			this.headers = headers;
		}
		if (headers != null && headers.size() > 0 && headers.containsKey("x-vlt-correlationId")) {
			this.correlationId = headers.getOrDefault("x-vlt-correlationId", "n/a");
		}
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

}
