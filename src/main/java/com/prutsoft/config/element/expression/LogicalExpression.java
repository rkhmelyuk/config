/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.expression;

import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.annotation.Nullable;

import java.util.Map;

/**
 * Expression that is evaluated to the boolean value.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public interface LogicalExpression extends Expression {

    /**
     * Evaluated the expression using context data.
     * If result of expression is not boolean value,
     * than returned {@code true} if result is not null and not 0.
     *
     * @param context the context map.
     * @return the result of evaluation.
     * @throws EvaluationException error to evaluate exception.
     */
    @NotNull
    Boolean evaluate(@Nullable Map<String, Object> context) throws EvaluationException;
}
