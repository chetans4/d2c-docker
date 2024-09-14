package com.d2c.notification.dto.resp;

import com.d2c.notification.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Wrapper class for request to accept additional information or metadata with
 * core Response data.
 *
 * @author ChetanChoudhary
 */
public class ResponseBodyWrapper<T> {

    private Integer responseCode;
    private String responseMessage;
    private ErrorWrapper error = new ErrorWrapper();
    
    private T responseData = (T) new ObjectNode(null);

    public ResponseBodyWrapper(Integer responseCode, String responseMessage, T responseData) {
        super();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseData = responseData;
    }

    public ResponseBodyWrapper(Integer responseCode, String responseMessage, ErrorWrapper error) {
        super();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.error = error;
    }

    public ResponseBodyWrapper(Integer responseCode, String responseMessage) {
        super();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public ResponseBodyWrapper() {
    }


    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public ErrorWrapper getError() {
        return error;
    }

    public void setError(ErrorWrapper error) {
        this.error = error;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return JsonUtil.objectToJson(this, false);
    }

}
