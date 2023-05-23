package com.cy.studentsel.handler.ex;

/**
 * @author leaf-fulture
 * @date 2023/5/22 15:20
 */
public class PasswordNoMatchException extends HandlerException{
    public PasswordNoMatchException() {
        super();
    }

    public PasswordNoMatchException(String message) {
        super(message);
    }

    public PasswordNoMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNoMatchException(Throwable cause) {
        super(cause);
    }

    protected PasswordNoMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
