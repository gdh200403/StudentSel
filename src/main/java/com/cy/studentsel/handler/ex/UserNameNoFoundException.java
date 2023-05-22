package com.cy.studentsel.handler.ex;

/**
 * @author leaf-fulture
 * @date 2023/5/22 15:19
 */
public class UserNameNoFoundException extends HandlerException{
    public UserNameNoFoundException() {
        super();
    }

    public UserNameNoFoundException(String message) {
        super(message);
    }

    public UserNameNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameNoFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNameNoFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
