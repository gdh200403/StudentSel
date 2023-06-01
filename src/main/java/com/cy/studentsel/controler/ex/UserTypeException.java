package com.cy.studentsel.controler.ex;

/**
 * @author leaf-fulture
 * @date 2023/6/1 16:35
 */
public class UserTypeException extends ControllerException{
    public UserTypeException() {
    }

    public UserTypeException(String message) {
        super(message);
    }

    public UserTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTypeException(Throwable cause) {
        super(cause);
    }

    public UserTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
