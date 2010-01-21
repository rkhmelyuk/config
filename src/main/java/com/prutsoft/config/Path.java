/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config;

import java.io.Serializable;

/**
 * The path to the property.
 *
 * @author Ruslan Khmelyuk
 * @since 1.6.9, 2010-01-21
 */
public class Path implements Serializable {

    private final String[] path;

    public Path(String path) {
        this.path = path.split(":");
    }

    public Path(String[] path) {
        this.path = path;
    }

    public String[] getPath() {
        return path;
    }

    public String getSingleName() {
        return (path.length > 0 ? path[0] : null);
    }

    public boolean isComplex() {
        return (path != null && path.length > 1);
    }

    public boolean isSimple() {
        return (path != null && path.length == 1);
    }

    public boolean isEmpty() {
        return (path == null || path.length == 0);
    }

    public Path getPathWithoutRoot() {
        String[] newPath = new String[path.length - 1];
        System.arraycopy(path, 1, newPath, 0, newPath.length);
        return new Path(newPath);
    }
}
