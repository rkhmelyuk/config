/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.expression;

import com.prutsoft.core.annotation.Nullable;
import com.prutsoft.core.utils.StringUtils;
import org.apache.commons.jexl.ExpressionFactory;
import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.JexlHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Expression written with Apache JEXL.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
class JexlExpression implements Expression {

    private static final Logger log = LoggerFactory.getLogger(JexlExpression.class);

    private final String expression;

    public JexlExpression(String expression) {
        this.expression = expression;
    }

    public Object evaluate(@Nullable Map<String, Object> context) throws EvaluationException {
        if (StringUtils.isEmptyTrimmed(expression)) {
            return null;
        }

        try {
            JexlContext jexlContext = JexlHelper.createContext();
            if (context != null) {
                jexlContext.setVars(context);
            }

            org.apache.commons.jexl.Expression e = ExpressionFactory.createExpression(expression);
            return e.evaluate(jexlContext);
        }
        catch (Exception e) {
            log.error("Can't prepare expression.:", e);
            throw new EvaluationException("Can't evaluate the expression: " + expression, e);
        }
    }
}
