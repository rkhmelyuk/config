/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.Version;
import com.prutsoft.config.exception.ConfigurationException;
import com.prutsoft.config.resource.ResourceRegistry;

/**
 * The interface of the configuration service.
 * Use configuration manager to access configuration,
 * load and destroy configuration, access to the services.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public interface ConfigurationService {

    /**
     * Gets the resource registry.
     *
     * @return the resource registry.
     */
    ResourceRegistry getResourceRegistry();

    /**
     * Gets the configuration reload service.
     *
     * @return the reload service.
     */
    ReloadService getReloadService();

    /**
     * Unloads all configurations and shutdown related services.
     */
    void destroy();

    /**
     * Loads specified configuration.
     *
     * @param configuration the configuration to load; can't be null.
     * @throws ConfigurationException error to load configuration.
     */
    void load(String configuration) throws ConfigurationException;

    /**
     * Unloads specified configuration.
     *
     * @param configuration the configuration to destroy; can't be null.
     * @return {@code true} if configuration was unloaded.
     */
    boolean unload(Configuration configuration);

    /**
     * Gets configuration with specified name.
     *
     * @param name the configuration name; can't be null.
     * @return the configuration with specified name or {@code null} if not found.
     */
    Configuration getConfiguration(String name);

    /**
     * Gets configuration with specified name and version
     *
     * @param name    the configuration name; can't be null.
     * @param version the configuration version; can't be null.
     * @return the configuration with specified name and version or {@code null} if not found.
     */
    Configuration getConfiguration(String name, Version version);

    /**
     * Gets configuration with specified name and version
     *
     * @param name    the configuration name; can't be null.
     * @param version the configuration version; can't be null.
     * @return the configuration with specified name and version or {@code null} if not found.
     */
    Configuration getConfiguration(String name, String version);
}
