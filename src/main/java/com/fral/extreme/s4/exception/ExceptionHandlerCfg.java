package com.fral.extreme.s4.exception;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface ExceptionHandlerCfg {

    int code();

    String message();
}
