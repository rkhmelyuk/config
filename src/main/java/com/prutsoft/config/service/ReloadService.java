/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.service;

import com.prutsoft.config.Configuration;

/**
 * This service is responsible for reloading changed resources.
 * Is useful to reload the configuration from the file after it was changed,
 * or from server after new configuration was published or old was updated etc.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public interface ReloadService {

    /**
     * Suspends all configuration reloads.
     */
    void suspend();

    /**
     * Resumes all configuration reloads.
     */
    void resume();

    /**
     * Suspends reload for specified configuration.
     * @param configuration the configuration to suspend reloads for.
     */
    void suspend(Configuration configuration);

    /**
     * Resumes reload for specified configuration.
     * @param configuration the configuration to suspend reloads for.
     */
    void resume(Configuration configuration);

    /**
     * Shutdown reloads for all configurations.
     */
    void shutdown();
    
}
