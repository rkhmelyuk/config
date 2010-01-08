package com.prutsoft.config.element.value;

/**
 * The String value type.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
class StringValueType extends AbstractValueType<String> {

    public String valueOf(String value) {
        return value;
    }

    public String stringOf(String value) {
        return value;
    }
}
