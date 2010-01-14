/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

/**
 * The wrapper for the configuration.
 *
 * @author Ruslan Khmelyuk
 * @since 1.1.0, 2010-01-14
 */
public interface ConfigurationWrapper {

    /**
     * Gets the wrapped configuration.
     * @return the wrapped configuration.
     */
    Configuration getConfiguration();

    /**
     * Sets the new wrapper configuration value.
     * @param configuration the new configuration to wrap.
     */
    void setConfiguration(Configuration configuration);
}
