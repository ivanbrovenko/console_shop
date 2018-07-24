package com.epam.shop.exception;

/**
 * Exception if there's no such a command
 */
public class NoSuchCommandException extends Exception {
    public NoSuchCommandException() {
    }

    public NoSuchCommandException(String message) {
        super(message);
    }

    public NoSuchCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCommandException(Throwable cause) {
        super(cause);
    }
}
