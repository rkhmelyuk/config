package com.prutsoft.config.exception;

/**
 * Error to access to the element value.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-04
 */
public class ValueAccessException extends RuntimeException {

    public ValueAccessException(String message) {
        super(message);
    }

    public ValueAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
