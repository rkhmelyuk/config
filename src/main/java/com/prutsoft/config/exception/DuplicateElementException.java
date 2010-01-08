package com.prutsoft.config.exception;

/**
 * Element with specified name already exists.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class DuplicateElementException extends ConfigurationException {

    public DuplicateElementException(String message) {
        super(message);
    }

    public DuplicateElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
