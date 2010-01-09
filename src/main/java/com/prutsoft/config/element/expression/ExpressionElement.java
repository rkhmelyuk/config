/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.expression;

import com.prutsoft.config.ContextMap;
import com.prutsoft.config.NamedElement;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * The expression element used to return calculated value.
 * It's just another kind of property.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class ExpressionElement implements NamedElement, Serializable {

    private final String name;
    private Expression expression;
    private String description;

    public ExpressionElement(String name) {
        this.name = name;
    }

    public ExpressionElement(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Object getValue(String name, Object value) throws EvaluationException {
        return expression.evaluate(Collections.singletonMap(name, value));
    }

    public Object getValue(Map<String, Object> context) throws EvaluationException {
        return expression.evaluate(context);
    }

    public Object getValue(ContextMap context) throws EvaluationException {
        return expression.evaluate(context != null ? context.getMap() : null);
    }

    /**
     * Gets expression element description.
     * @return the expression element description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets expression element description.
     * @param description the expression element description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
