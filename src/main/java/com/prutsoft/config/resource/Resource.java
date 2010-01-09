/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.resource;

import java.io.InputStream;

/**
 * The interface for the configuration resource.
 * Supports few important functions like get content as input stream or
 * get name or check whether was changed etc.
 * <p>
 * Resource implementation should have constructor that gets 0 or only string parameter.
 * This string can be resource name or content etc - it depends on implementation.
 * <p>
 * Implement this interface to create custom resource and register it in
 * {@link com.prutsoft.config.resource.ResourceRegistry} with appropriate prefix. 
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-05
 *
 * @see com.prutsoft.config.resource.ResourceRegistry
 */
public interface Resource {

    /**
     * Gets the resource content as input stream
     *
     * @return the resource input stream.
     * @throws ResourceLoadException error to load resource as input stream.
     */
    InputStream getInputStream() throws ResourceLoadException;

    /**
     * Gets the resource name.
     *
     * @return the resource name.
     */
    String getName();

    /**
     * Checks whether current resource was changed from last time it was accessed.
     *
     * @return {@code true} if resource was changed, otherwise {@code false}.
     */
    boolean isChanged();

    /**
     * Checks whether resource exists.
     *
     * @return {@code true} if resource exists, otherwise {@code false}.
     */
    boolean exists();

    /**
     * Reloads resource.
     * @return {@code true} if resource was reloaded successfully, otherwise {@code false}.
     */
    boolean reload();


}
