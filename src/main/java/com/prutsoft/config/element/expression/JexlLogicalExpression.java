package com.prutsoft.config.element.expression;

import com.prutsoft.core.annotation.Nullable;

import java.util.Map;

/**
 * The logical expression using Apache JEXL.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
class JexlLogicalExpression extends JexlExpression implements LogicalExpression{

    public JexlLogicalExpression(String expression) {
        super(expression);
    }

    @Override
    public Boolean evaluate(@Nullable Map<String, Object> context) throws EvaluationException {
        Object result = super.evaluate(context);

        if (result instanceof Boolean) {
            return (Boolean) result;
        }

        if (result != null) {
            if (result instanceof Number) {
                Number number = (Number) result;
                return (number.intValue() != 0);
            }
            return true;
        }

        return false;
    }
}
