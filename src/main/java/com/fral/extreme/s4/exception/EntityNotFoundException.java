package com.fral.extreme.s4.exception;

import java.io.Serializable;

@ExceptionHandlerCfg(code = 404, message = "Entity with the provided parameters can not be found.")
public class EntityNotFoundException extends S4SystemException {

    private Serializable entityId;
    private String entityName;

    public EntityNotFoundException(Serializable entityId, String entityName) {
        this.entityId = entityId;
        this.entityName = entityName;
    }

    @Override
    protected void accept(ErrorResponseBuilder builder) {
        builder.addErrorData("entityId", entityId);
        builder.addErrorData("entityName", entityName);
    }
}
