/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser;

import com.prutsoft.config.element.control.Condition;
import com.prutsoft.config.element.value.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * The builder buffer for intermediate values.
 * 
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-07
 */
public class BuilderBuffer {
    
    private Value value;
    private String description;
    private List<Condition> conditions = new ArrayList<Condition>();

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void clear() {
        setDescription(null);
        setValue(null);
        getConditions().clear();
    }
}
