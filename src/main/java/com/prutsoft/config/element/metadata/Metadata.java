/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.metadata;

import com.prutsoft.core.asserts.ArgumentAssert;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * The configuration metadata with it's properties.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class Metadata implements Serializable {

    private static final String AUTHOR = "Author";
    private static final String DESCRIPTION = "Description";

    private final Map<String, MetadataProperty> properties = new TreeMap<String, MetadataProperty>();

    /**
     * Gets the property by name.
     *
     * @param name the name of the property.
     * @return the property with specified name or {@code null} if not found.
     */
    public MetadataProperty getProperty(String name) {
        return properties.get(name);
    }

    /**
     * Gets the value of the property by name.
     *
     * @param name the name of the property.
     * @return the property value or {@code null} if property is not found.
     */
    public String getPropertyValue(String name) {
        MetadataProperty property = properties.get(name);
        return (property != null ? property.getValue() : null);
    }

    /**
     * Adds metadata property.
     * Property will be not added if there is property with such name; instead {@code false} will be returned.
     *
     * @param property the property to add; can't be null.
     * @return {@code true} if property was added, otherwise {@code false}.
     */
    public boolean addProperty(MetadataProperty property) {
        ArgumentAssert.isNotNull(property, "Property can't be null.");

        if (hasProperty(property.getName())) return false;
        properties.put(property.getName(), property);
        return true;
    }

    /**
     * Removes property with specified name.
     *
     * @param name the name of the property to remove.
     * @return {@code true} if property is removed; otherwise {@code false}.
     */
    public boolean removeProperty(String name) {
        if (hasProperty(name)) {
            properties.remove(name);
            return true;
        }
        return false;
    }

    /**
     * Checks whether property with specified name exists.
     *
     * @param name the name of the property.
     * @return {@code true} if property exists, otherwise {@code false}.
     */
    public boolean hasProperty(String name) {
        return properties.containsKey(name);
    }

    /**
     * Gets 'Author' property value.
     * @return the author property value.
     */
    public String getAuthor() {
        MetadataProperty property = getProperty(AUTHOR);
        return property != null ? property.getValue() : null;
    }

    /**
     * Gets 'Description' property value.
     * @return the description property value.
     */
    public String getDescription() {
        MetadataProperty property = getProperty(DESCRIPTION);
        return property != null ? property.getValue() : null;
    }

}
