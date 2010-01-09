/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.value;

import com.prutsoft.config.exception.ValueFormatException;

/**
 * The value type interface. Used to convert values from and to String.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
public interface ValueType<T> {

    /**
     * Converts string to value.
     *
     * @param value the string to convert to value.
     * @return the converted result of string value.
     * @throws com.prutsoft.config.exception.ValueFormatException error to get value from string.
     */
    T valueOf(String value) throws ValueFormatException;

    /**
     * Converts value to the string.
     *
     * @param value the value to convert to string.
     * @return the string as result of conversion value to string.
     * @throws com.prutsoft.config.exception.ValueFormatException error to get string from value.
     */
    String stringOf(T value) throws ValueFormatException;
}
