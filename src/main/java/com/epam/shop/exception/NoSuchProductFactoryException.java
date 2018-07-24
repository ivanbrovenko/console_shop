package com.epam.shop.exception;

/**
 * If there's no such product factory
 */
public class NoSuchProductFactoryException extends Exception {

    public NoSuchProductFactoryException() {
    }

    public NoSuchProductFactoryException(String message) {
        super(message);
    }

    public NoSuchProductFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
