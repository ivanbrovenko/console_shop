package com.epam.shop.exception;

public class ApplicationTechnicalException extends Exception {
    public ApplicationTechnicalException() {
    }

    public ApplicationTechnicalException(String message) {
        super(message);
    }

    public ApplicationTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationTechnicalException(Throwable cause) {
        super(cause);
    }
}
