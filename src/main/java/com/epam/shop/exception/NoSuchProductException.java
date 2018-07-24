package com.epam.shop.exception;

/**
 * Exception if there's no such a gadget
 */
public class NoSuchProductException extends Exception {
    public NoSuchProductException() {
    }

    public NoSuchProductException(String message) {
        super(message);
    }

    public NoSuchProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchProductException(Throwable cause) {
        super(cause);
    }
}
