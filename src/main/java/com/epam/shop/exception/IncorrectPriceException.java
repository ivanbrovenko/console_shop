package com.epam.shop.exception;

public class IncorrectPriceException extends Exception {
    public IncorrectPriceException() {
    }

    public IncorrectPriceException(String message) {
        super(message);
    }

    public IncorrectPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPriceException(Throwable cause) {
        super(cause);
    }
}
