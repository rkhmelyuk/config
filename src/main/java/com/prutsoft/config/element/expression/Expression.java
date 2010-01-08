package com.prutsoft.config.element.expression;

import com.prutsoft.core.annotation.Nullable;

import java.util.Map;

/**
 * The expression that returns value.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public interface Expression {

    /**
     * Evaluates expression using data from context.
     *
     * @param context the context map.
     * @return the result of expression evaluation.
     * @throws EvaluationException error to evaluate expression.
     */
    @Nullable
    Object evaluate(@Nullable Map<String, Object> context) throws EvaluationException;
}
