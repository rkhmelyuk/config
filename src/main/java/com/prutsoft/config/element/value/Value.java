package com.prutsoft.config.element.value;

import com.prutsoft.config.exception.ValueFormatException;
import com.prutsoft.core.annotation.NotNull;

/**
 * The value of the property.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public interface Value<T> {

    /**
     * Gets the value.
     * @return the value.
     */
    T getValue();

    /**
     * Sets new value.
     * @param value the new value
     */
    void setValue(T value);

    /**
     * Gets value type.
     * @return the value type.
     */
    @NotNull
    ValueType<T> getType();

    /**
     * Gets value as string.
     * @return value as string.
     * @throws com.prutsoft.config.exception.ValueFormatException error to convert value to string.
     */
    String getStringValue() throws ValueFormatException;
}
