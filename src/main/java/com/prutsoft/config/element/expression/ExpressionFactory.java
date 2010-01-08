package com.prutsoft.config.element.expression;

import com.prutsoft.core.asserts.ArgumentAssert;

/**
 * The Expressions factory.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-04
 */
public final class ExpressionFactory {

    public static Expression createExpression(String expression) {
        ArgumentAssert.isNotEmpty(expression, "Expression is required");
        return new JexlExpression(expression);
    }

    public static LogicalExpression createLogicalExpression(String expression) {
        ArgumentAssert.isNotEmpty(expression, "Expression is required");
        return new JexlLogicalExpression(expression);
    }


    private ExpressionFactory() {}

}
