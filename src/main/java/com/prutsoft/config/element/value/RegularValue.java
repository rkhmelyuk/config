/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.value;

import com.prutsoft.config.exception.ValueFormatException;
import com.prutsoft.core.ToStringBuilder;
import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.utils.ObjectUtils;

import java.io.Serializable;

/**
 * The implementation of property value.
 * Parses string to value on creation.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
public class RegularValue<T> implements Value<T>, Serializable {

    public static <T> RegularValue<T> create(String value, ValueType<T> type) throws ValueFormatException {
        ArgumentAssert.isNotNull(type, "Type can't be null.");
        return new RegularValue<T>(type.valueOf(value), type);
    }

    // ------------------------------------------------------------

    private T value;
    private final ValueType<T> type;

    RegularValue(T value, ValueType<T> type) {
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getStringValue() throws ValueFormatException {
        return type.stringOf(value);
    }

    public ValueType<T> getType() {
        return type;
    }

    // --------------------------------------------------------

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(value, type);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RegularValue)) return false;

        RegularValue other = (RegularValue) o;

        return ObjectUtils.equals(value, other.value) &&
                ObjectUtils.equals(type, other.type);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this.getClass(), 30)
                .field("Value", value)
                .field("Type", type)
                .toString();
    }
}
