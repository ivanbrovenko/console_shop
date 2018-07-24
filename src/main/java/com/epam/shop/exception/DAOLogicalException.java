package com.epam.shop.exception;

/**
 * Exception that threw if something wrong in
 * database logic
 */
public class DAOLogicalException extends Exception {
    public DAOLogicalException() {
    }

    public DAOLogicalException(String message) {
        super(message);
    }

    public DAOLogicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOLogicalException(Throwable cause) {
        super(cause);
    }
}
