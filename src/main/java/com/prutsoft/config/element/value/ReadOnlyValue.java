package com.prutsoft.config.element.value;

import com.prutsoft.config.exception.ValueFormatException;
import com.prutsoft.core.asserts.ArgumentAssert;

/**
 * The read only value.
 * Operation <code>setValue()</code> will throw {@link UnsupportedOperationException} exception.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class ReadOnlyValue<T> extends RegularValue<T> {

    public static <T> ReadOnlyValue<T> create(String value, ValueType<T> type) throws ValueFormatException {
        ArgumentAssert.isNotNull(type, "Type can't be null.");
        return new ReadOnlyValue<T>(type.valueOf(value), type);
    }

    // ------------------------------------------------------------

    public ReadOnlyValue(T value, ValueType<T> tValueType) {
        super(value, tValueType);
    }

    @Override
    public void setValue(T value) {
        throw new UnsupportedOperationException("Operation is not supported in ReadOnlyValue.");
    }
}
