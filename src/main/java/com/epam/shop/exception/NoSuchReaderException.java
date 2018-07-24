package com.epam.shop.exception;

/**
 * If there's no such filler
 */
public class NoSuchReaderException extends Exception {
    public NoSuchReaderException() {
    }

    public NoSuchReaderException(String message) {
        super(message);
    }

    public NoSuchReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchReaderException(Throwable cause) {
        super(cause);
    }
}
