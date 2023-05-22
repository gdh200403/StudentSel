package com.cy.studentsel.handler.ex;

/**
 * @author leaf-fulture
 * @date 2023/5/22 15:09
 */
public class HandlerSqlException extends HandlerException{
    public HandlerSqlException() {
        super();
    }

    public HandlerSqlException(String message) {
        super(message);
    }

    public HandlerSqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerSqlException(Throwable cause) {
        super(cause);
    }

    protected HandlerSqlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
