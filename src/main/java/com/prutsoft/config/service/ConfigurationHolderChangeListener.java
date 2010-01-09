/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.resource.Resource;

/**
 * Listener for any change withing configuration holder.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public interface ConfigurationHolderChangeListener {
    
    /**
     * Called on change related with specified configuration and resource.
     *
     * @param configuration the configuration.
     * @param resource the resource.
     */
    void process(Configuration configuration, Resource resource);
}
