package com.epam.shop.exception;

/**
 * Exception that threw in case date typed incorrectly
 */
public class IncorrectDateException extends Exception {
    public IncorrectDateException() {
    }

    public IncorrectDateException(String message) {
        super(message);
    }

    public IncorrectDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDateException(Throwable cause) {
        super(cause);
    }
}
