/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.element.control;

import com.prutsoft.config.ContextMap;
import com.prutsoft.config.NamedElement;
import com.prutsoft.config.element.expression.EvaluationException;
import com.prutsoft.config.element.value.Value;
import com.prutsoft.core.asserts.ArgumentAssert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * The switch element used to return only one result from a set of others.
 * Condition is used to choose need result.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class SwitchElement implements NamedElement, Serializable {

    private final String name;
    private final List<Condition<Value>> conditions;

    public SwitchElement(String name) {
        this.name = name;
        this.conditions = new ArrayList<Condition<Value>>();
    }

    public String getName() {
        return name;
    }

    public void addCondition(Condition<Value> condition) {
        ArgumentAssert.isNotNull(condition, "Condition can't be null.");
        conditions.add(condition);
    }

    public void removeCondition(Condition<Value> condition) {
        ArgumentAssert.isNotNull(condition, "Condition can't be null.");
        conditions.remove(condition);
    }

    public List<Condition<Value>> getConditions() {
        return Collections.unmodifiableList(conditions);
    }

    /**
     * Gets the value for the switch element.
     *
     * @param context the context with variables for the condition elements.
     * @return the value or {@code null} if no value found.
     * @throws EvaluationException error to evaluate expressions and get the value.
     */
    public Value getValue(Map<String, Object> context) throws EvaluationException {
        Condition<Value> elseCondition = null;
        for (Condition<Value> each : conditions) {
            if (each instanceof ElseCondition) {
                elseCondition = each;
            }
            else if (each.getExpression().evaluate(context)) {
                return each.getValue();
            }
        }
        return elseCondition != null ? elseCondition.getValue() : null;
    }

    /**
     * Gets the value for the switch element.
     *
     * @param context the context with variables for the condition elements.
     * @return the value or {@code null} if no value found.
     * @throws EvaluationException error to evaluate expressions and get the value.
     */
    public Value getValue(ContextMap context) throws EvaluationException {
        return getValue(context != null ? context.getMap() : null);
    }

}
