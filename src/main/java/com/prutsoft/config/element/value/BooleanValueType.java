package com.prutsoft.config.element.value;

/**
 * The Boolean value type.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
class BooleanValueType extends AbstractValueType<Boolean> {

    public Boolean valueOf(String value) {
        return value != null ? Boolean.valueOf(value) : null;
    }

    public String stringOf(Boolean value) {
        return value != null ? Boolean.toString(value) : null;
    }
}
