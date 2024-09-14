package com.d2c.payment.exception;

import java.util.Map;

public class ValidationException extends ApplicationException{

    private Map status;

    public ValidationException(Map status) {
        this.status = status;
    }

    public ValidationException(String message) {
        super(message);
    }
}