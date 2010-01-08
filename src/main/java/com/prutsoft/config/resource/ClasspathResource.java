package com.prutsoft.config.resource;

import com.prutsoft.core.utils.ClassLoaderUtils;

import java.io.InputStream;

/**
 * The classpath file resource.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-05
 */
public class ClasspathResource implements Resource {

    private final String resource;

    public ClasspathResource(String resource) {
        this.resource = resource;
    }

    public InputStream getInputStream() {
        return ClassLoaderUtils.loadResource(resource, getClassLoader());
    }

    public String getName() {
        return resource;
    }

    public boolean isChanged() {
        return false;
    }

    public boolean exists() {
        return resource != null && getClassLoader().getResource(resource) != null;
    }

    public boolean reload() {
        return exists();
    }

    private ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }
}
