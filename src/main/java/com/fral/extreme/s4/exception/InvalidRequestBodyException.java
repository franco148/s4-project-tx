package com.fral.extreme.s4.exception;

@ExceptionHandlerCfg(code = 404, message = "Invalid arguments for the requested operation.")
public class InvalidRequestBodyException extends S4SystemException {

    private Object invalidRequestBody;

    public InvalidRequestBodyException(Object invalidRequestBody) {
        this.invalidRequestBody = invalidRequestBody;
    }

    @Override
    protected void accept(ErrorResponseBuilder builder) {
        builder.addErrorData("requestBody", invalidRequestBody);
    }
}
