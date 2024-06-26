package com.cy.studentsel.handler.ex;

/**
 * @author leaf-fulture
 * @date 2023/5/22 14:50
 */
public class HandlerException extends RuntimeException {
    public HandlerException() {
        super();
    }

    public HandlerException(String message) {
        super(message);
    }

    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerException(Throwable cause) {
        super(cause);
    }

    protected HandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
