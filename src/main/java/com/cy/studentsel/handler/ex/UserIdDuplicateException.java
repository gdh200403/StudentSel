package com.cy.studentsel.handler.ex;

/**
 * @author leaf-fulture
 * @date 2023/5/26 16:44
 */
public class UserIdDuplicateException extends HandlerException{
    public UserIdDuplicateException() {
    }

    public UserIdDuplicateException(String message) {
        super(message);
    }

    public UserIdDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIdDuplicateException(Throwable cause) {
        super(cause);
    }

    public UserIdDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
