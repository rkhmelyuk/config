/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.annotation.Nullable;

import java.util.Collection;

/**
 * The interface for named elements container type.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-05
 */
public interface NamedElementsContainer {

    /**
     * Gets the element with specified name.
     *
     * @param name the name of the element.
     * @return the element with specified name or {@code null} if not found.
     */
    @Nullable
    NamedElement getElement(String name);

    /**
     * Gets the unmodifiable set of elements.
     *
     * @return the unmodifiable set of elements.
     */
    @NotNull
    Collection<NamedElement> getElements();

    /**
     * Add the element to a container. If element with such name exists, than returns {@code false}.
     *
     * @param property the element to add, can't be null.
     * @return {@code true} if element was added, otherwise {@code false}.
     */
    boolean addElement(NamedElement property);


    /**
     * Removes element with specified name.
     *
     * @param name the element name.
     * @return {@code true} if element removed, otherwise {@code false}.
     */
    boolean removeElement(String name);

    /**
     * Checks whether set has element with specified name.
     *
     * @param name the element name.
     * @return {@code true} if there is such element in a set.
     */
    boolean hasElement(String name);

}
