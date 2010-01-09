/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.control;

import com.prutsoft.config.element.expression.EvaluationException;
import com.prutsoft.config.element.expression.LogicalExpression;
import com.prutsoft.core.annotation.Nullable;

import java.io.Serializable;
import java.util.Map;

/**
 * The condition with specified result.
 * Returns value only if condition expression evaluates to {@code true}.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class Condition<T> implements Serializable {

    private LogicalExpression expression;
    private T value;

    public Condition(LogicalExpression expression, T value) {
        this.expression = expression;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public LogicalExpression getExpression() {
        return expression;
    }

    /**
     * Evaluates condition and return value if it's success.
     * Otherwise returns {@code null}.
     *
     * @param context the evaluation context.
     * @return the value if evaluated to {@code true}, otherwise {@code null}.
     * @throws EvaluationException error to evaluate expression. 
     */
    @Nullable
    public T getValueOnCondition(@Nullable Map<String, Object> context) throws EvaluationException {
        return (expression.evaluate(context) ? value : null);
    }
}
