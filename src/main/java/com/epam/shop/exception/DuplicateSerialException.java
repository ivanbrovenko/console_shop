package com.epam.shop.exception;


public class DuplicateSerialException extends Exception {
    public DuplicateSerialException() {
    }

    public DuplicateSerialException(String message) {
        super(message);
    }

    public DuplicateSerialException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateSerialException(Throwable cause) {
        super(cause);
    }
}
