/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.control;

import com.prutsoft.config.element.expression.EvaluationException;
import com.prutsoft.config.element.expression.LogicalExpression;
import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.annotation.Nullable;

import java.util.Map;

/**
 * This kind of condition don't have any expression.
 * Instead it always returns value.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-04
 */
public class ElseCondition<T> extends Condition<T> {
    
    public ElseCondition(T value) {
        super(new TrueLogicalExpression(), value);
    }

    /**
     * The logical expression that always returns {@code true}.
     */
    private static class TrueLogicalExpression implements LogicalExpression {
        @NotNull
        public Boolean evaluate(@Nullable Map<String, Object> context) throws EvaluationException {
            return true;
        }
    }
}
