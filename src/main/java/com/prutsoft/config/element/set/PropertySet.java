/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.set;

import com.prutsoft.config.NamedElement;
import com.prutsoft.config.NamedElementsContainer;
import com.prutsoft.core.asserts.ArgumentAssert;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Set of elements. Used to organize elements within group.
 * Has name and can't contain any other element except property.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class PropertySet implements NamedElement, NamedElementsContainer, Serializable {

    private final String name;
    private String description;
    private Map<String, NamedElement> elements = new HashMap<String, NamedElement>();

    public PropertySet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets elements set description.
     * @return the elements set description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets elements set description.
     * @param description the elements set new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public NamedElement getElement(String name) {
        return elements.get(name);
    }

    public Collection<NamedElement> getElements() {
        return Collections.unmodifiableCollection(elements.values());
    }

    public boolean addElement(NamedElement element) {
        ArgumentAssert.isNotNull(element, "Element can't be null.");
        if (hasElement(element.getName())) return false;
        elements.put(element.getName(), element);
        return true;
    }

    public boolean removeElement(String name) {
        return (elements.remove(name) != null);
    }

    public boolean hasElement(String name) {
        return elements.containsKey(name);
    }

}
