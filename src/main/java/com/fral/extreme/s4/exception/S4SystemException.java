package com.fral.extreme.s4.exception;

public abstract class S4SystemException extends RuntimeException {

    protected abstract void accept(ErrorResponseBuilder builder);
}
