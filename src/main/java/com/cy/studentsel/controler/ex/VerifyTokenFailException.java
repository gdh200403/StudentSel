package com.cy.studentsel.controler.ex;

/**
 * @author leaf-fulture
 * @date 2023/6/3 16:22
 */
public class VerifyTokenFailException extends ControllerException {
    public VerifyTokenFailException() {
    }

    public VerifyTokenFailException(String message) {
        super(message);
    }

    public VerifyTokenFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerifyTokenFailException(Throwable cause) {
        super(cause);
    }

    public VerifyTokenFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
