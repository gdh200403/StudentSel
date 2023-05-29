package com.cy.studentsel.controler.ex;

/**
 * @author leaf-fulture
 * @date 2023/5/29 16:28
 */
public class UsernameEmptyException extends ControllerException {

    public UsernameEmptyException() {
    }

    public UsernameEmptyException(String message) {
        super(message);
    }

    public UsernameEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameEmptyException(Throwable cause) {
        super(cause);
    }

    public UsernameEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
