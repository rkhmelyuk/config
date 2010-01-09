/*
 * Copyright (c) 2010 Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser.xml;

import com.prutsoft.config.element.control.Condition;
import com.prutsoft.config.element.control.ElseCondition;
import com.prutsoft.config.element.expression.ExpressionFactory;
import com.prutsoft.config.element.expression.LogicalExpression;
import com.prutsoft.config.element.value.Value;
import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.code.Warnings;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The condition element parser.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class ConditionParser implements ElementParser {

    private ElementParser valueParser;

    public ElementParser getValueParser() {
        if (valueParser == null) {
            valueParser = new ValueParser();
        }
        return valueParser;
    }

    public void setValueParser(ElementParser valueParser) {
        this.valueParser = valueParser;
    }

    public boolean canParse(Node node) {
        return node != null && (node.getNodeName().equals("on") || node.getNodeName().equals("else"));
    }

    public void parse(Node node, ConfigurationBuilder builder) throws ParseException {
        ArgumentAssert.isNotNull(node, "Node can't be null.");
        ArgumentAssert.isNotNull(builder, "Configuration builder can't be null.");

        final Value value = parserValue(node, builder);
        builder.getBuffer().getConditions().add(createCondition(node, value));
    }

    private Value parserValue(Node node, ConfigurationBuilder builder) throws ParseException {
        final ElementParser parser = getValueParser();
        final NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            final Node child = children.item(i);
            if (child == null) continue;

            if (parser.canParse(child)) {
                parser.parse(child, builder);
                return builder.getBuffer().getValue();
            }
        }
        return null;
    }

    @SuppressWarnings(Warnings.Unchecked)
    private Condition  createCondition(Node node, Value value) throws ParseException {
        final String nodeName = node.getNodeName();

        Condition condition;
        if (nodeName.equals("on")) {
            LogicalExpression expression = ExpressionFactory.createLogicalExpression(
                    ParserUtil.parseAttribute(node, "condition", true));
            condition = new Condition(expression, value);
        }
        else if (nodeName.equals("else")) {
            condition = new ElseCondition(value);
        }
        else {
            throw new ParseException("Unexpected element " + nodeName);
        }

        return condition;
    }
}
