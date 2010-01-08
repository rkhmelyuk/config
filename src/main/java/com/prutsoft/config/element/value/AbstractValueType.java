package com.prutsoft.config.element.value;

/**
 * Abstract value type.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-04
 */
abstract class AbstractValueType<T> implements ValueType<T> {

    @Override
    public String toString() {
        return "ValueType[" + this.getClass().getSimpleName() + "]";
    }
}
