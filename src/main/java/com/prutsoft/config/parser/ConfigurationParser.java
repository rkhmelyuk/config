/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.resource.Resource;
import com.prutsoft.core.annotation.NotNull;

/**
 * Configuration parser interface with only one responsibility:
 * parse resource and return Configuration instance.
 * <p>
 * Implement this interface and register implementation to introduce
 * parser for new format.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public interface ConfigurationParser {

    /**
     * Parses the resource and returns the configuration.
     * Resource also must exist.
     *
     * @param resource the resource with configuration; can't be null.
     * @return the parsed configuration.
     * @throws com.prutsoft.config.exception.ParseException error to parse configuration.
     */
    @NotNull
    Configuration parse(@NotNull Resource resource) throws ParseException;

}
