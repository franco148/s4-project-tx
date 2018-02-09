package com.fral.extreme.s4.exception;

import java.util.Map;

class ErrorResponse {

    private String exception;

    private Map<String, Object> data;

    public ErrorResponse(String exception, Map<String, Object> data) {
        this.exception = exception;
        this.data = data;
    }

    public String getException() {
        return exception;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
