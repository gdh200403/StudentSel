package com.cy.studentsel.controler.ex;

/**
 * @author leaf-fulture
 * @date 2023/6/3 16:13
 */
public class NoTokenException extends ControllerException{
    public NoTokenException() {
    }

    public NoTokenException(String message) {
        super(message);
    }

    public NoTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTokenException(Throwable cause) {
        super(cause);
    }

    public NoTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
