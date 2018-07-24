package com.epam.shop.exception;

/**
 * Exception that threw in case there's no closest date at all
 */
public class NoClosestDateException extends Exception {
    public NoClosestDateException() {
    }

    public NoClosestDateException(String message) {
        super(message);
    }

    public NoClosestDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoClosestDateException(Throwable cause) {
        super(cause);
    }
}
