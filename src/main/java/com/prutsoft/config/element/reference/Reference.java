/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.reference;

import com.prutsoft.config.NamedElement;
import com.prutsoft.core.ToStringBuilder;

/**
 * The reference to the property value.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-16
 */
public class Reference implements NamedElement {

    private final String name;
    private String value;

    public Reference(String name) {
        this.name = name;
    }

    public Reference(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this.getClass(), 20)
                .field("Name", name)
                .field("Value", name)
                .toString();
    }
}
