package com.prutsoft.config.service;

import com.prutsoft.config.ConfigurationWrapper;
import com.prutsoft.config.exception.ConfigurationException;
import com.prutsoft.config.exception.ConfigurationLoadException;
import com.prutsoft.config.resource.Resource;
import com.prutsoft.core.annotation.NotNull;

import java.util.List;

/**
 * The configuration loader.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public interface ConfigurationLoader {

    /**
     * Loads the configuration and store it to the holder.
     *
     * @param configuration the configuration to load; can't be null or empty.
     * @param holder        the configurations holder.
     * @throws ConfigurationException error to load the configuration.
     */
    void load(@NotNull String configuration, @NotNull ConfigurationHolder holder) throws ConfigurationException;

    /**
     * Loads the configurations and store them to the holder. Rather all or none configuration is loaded.
     * If there is error to load any configuration than none will be stored to use.
     *
     * @param configurations the configurations to load; can't be null or empty.
     * @param holder         the configurations holder.
     * @throws ConfigurationException error to load the configuration.
     */
    void load(@NotNull List<String> configurations, @NotNull ConfigurationHolder holder) throws ConfigurationException;

    /**
     * Reloads the configuration using specified resource.
     *
     * @param configuration the configuration to reload;
     * @param resource the configuration resource to reload from.
     * @throws ConfigurationLoadException error to reload configuration.
     */
    void reload(@NotNull ConfigurationWrapper configuration, @NotNull Resource resource) throws ConfigurationLoadException;


}
