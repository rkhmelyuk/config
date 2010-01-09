/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.metadata;

import com.prutsoft.config.NamedElement;
import com.prutsoft.core.ToStringBuilder;
import com.prutsoft.core.annotation.Nullable;
import com.prutsoft.core.utils.ObjectUtils;

/**
 * The metadata property element.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-04
 */
public class MetadataProperty implements NamedElement {

    private final String name;
    private String value;

    public MetadataProperty(String name) {
        this.name = name;
    }

    public MetadataProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public String getValue() {
        return value;
    }

    public void setValue(@Nullable String value) {
        this.value = value;
    }

    // -------------------------------------------------------------

    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MetadataProperty)) return false;

        final MetadataProperty other = (MetadataProperty) o;

        return ObjectUtils.equals(name, other.name) &&
                ObjectUtils.equals(value, other.value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(MetadataProperty.class, 30)
                .field("Name", name)
                .field("Value", value)
                .toString();
    }
}
