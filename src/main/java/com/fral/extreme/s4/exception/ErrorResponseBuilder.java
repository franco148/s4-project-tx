package com.fral.extreme.s4.exception;

import java.util.HashMap;
import java.util.Map;

final class ErrorResponseBuilder {

    private String exception;

    private Map<String, Object> data;

    public static ErrorResponseBuilder getInstance(String exception) {
        return new ErrorResponseBuilder(exception);
    }

    private ErrorResponseBuilder(String exception) {
        this.exception = exception;
        this.data = new HashMap<>();
    }

    public ErrorResponseBuilder addErrorData(String key, Object value) {
        data.put(key, value);

        return this;
    }

    public ErrorResponse build() {
        return new ErrorResponse(exception, data);
    }
}
