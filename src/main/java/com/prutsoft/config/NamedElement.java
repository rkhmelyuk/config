/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

/**
 * The element of configuration that has a name.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public interface NamedElement {

    /**
     * Gets element name.
     * @return the element name.
     */
    String getName();
}
