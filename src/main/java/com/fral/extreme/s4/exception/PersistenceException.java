package com.fral.extreme.s4.exception;

@ExceptionHandlerCfg(code = 500, message = "Error in entity persistence process.")
public class PersistenceException extends S4SystemException {

    private String entityType;

    public PersistenceException(String entityType) {
        this.entityType = entityType;
    }

    @Override
    protected void accept(ErrorResponseBuilder builder) {
        builder.addErrorData("entity", entityType);
    }
}
