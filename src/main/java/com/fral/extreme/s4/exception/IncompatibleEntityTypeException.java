package com.fral.extreme.s4.exception;

@ExceptionHandlerCfg(code = 500, message = "Invalid Entity Type for the requested operation.")
public class IncompatibleEntityTypeException extends S4SystemException {

    private String operation;
    private String incompatibleType;
    private String expectedType;


    public IncompatibleEntityTypeException(String operation, String incompatibleType, String expectedType) {
        this.operation = operation;
        this.incompatibleType = incompatibleType;
        this.expectedType = expectedType;
    }

    @Override
    protected void accept(ErrorResponseBuilder builder) {
        builder.addErrorData("operation", operation);
        builder.addErrorData("incompatibleType", incompatibleType);
        builder.addErrorData("expectedType", expectedType);
    }
}
