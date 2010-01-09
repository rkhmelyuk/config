/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.reload;

import com.prutsoft.core.ToStringBuilder;

import java.io.Serializable;

/**
 * The configuration reload policy.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public class ReloadPolicy implements Serializable {

    /**
     * If set to true than should check for configuration
     * resource changes to reload configuration.
     */
    private boolean onChange;

    /**
     * The period in seconds how often reload service should
     * check for changes in the configuration resource.
     */
    private int checkEvery;

    public boolean isOnChange() {
        return onChange;
    }

    public void setOnChange(boolean onChange) {
        this.onChange = onChange;
    }

    public int getCheckEvery() {
        return checkEvery;
    }

    public void setCheckEvery(int checkEvery) {
        this.checkEvery = checkEvery;
    }

    // -------------------------------------------------------

    @Override
    public String toString() {
        return new ToStringBuilder(this.getClass(), 20)
                .field("OnChange", onChange)
                .field("CheckEvery, s", (checkEvery/1000))
                .toString();
    }

}
