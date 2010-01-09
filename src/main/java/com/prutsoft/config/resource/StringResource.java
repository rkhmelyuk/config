/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.resource;

import com.prutsoft.core.utils.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * The resource that represents regular string.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-05
 */
public class StringResource implements Resource {

    private String content;
    boolean changed = false;

    public StringResource(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        changed = !StringUtils.equals(this.content, content); 
        this.content = content;
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(content.getBytes());
    }

    public String getName() {
        return null;
    }

    public boolean isChanged() {
        return changed;
    }

    public boolean exists() {
        return content != null;
    }

    public boolean reload() {
        return exists();
    }
}
