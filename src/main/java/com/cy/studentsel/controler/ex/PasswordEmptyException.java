package com.cy.studentsel.controler.ex;

/**
 * @author leaf-fulture
 * @date 2023/5/29 16:28
 */
public class PasswordEmptyException extends ControllerException{
    public PasswordEmptyException() {
    }

    public PasswordEmptyException(String message) {
        super(message);
    }

    public PasswordEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordEmptyException(Throwable cause) {
        super(cause);
    }

    public PasswordEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
