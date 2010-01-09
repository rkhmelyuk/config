/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * The file resource. Use it to read data from regular file.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-05
 */
public class FileResource implements Resource {

    private final String fileName;
    private File file;
    private long timestamp;

    public FileResource(String fileName) {
        this.fileName = fileName;
        file = new File(fileName);
    }

    public InputStream getInputStream() throws ResourceLoadException {
        try {
            timestamp = file.lastModified();
            return new FileInputStream(file);
        }
        catch (Exception e) {
            throw new ResourceLoadException("Error to load file " + fileName + ", cause:", e);
        }
    }

    public String getName() {
        return fileName;
    }

    public boolean isChanged() {
        return timestamp == 0 || timestamp < file.lastModified();
    }

    public boolean exists() {
        return file.exists();
    }

    public boolean reload() {
        return exists();
    }
}
