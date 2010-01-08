package com.prutsoft.config.parser.xml;

import com.prutsoft.config.element.control.Condition;
import com.prutsoft.config.element.control.SwitchElement;
import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.code.Warnings;
import com.prutsoft.core.utils.collections.ListUtils;
import org.w3c.dom.Node;

/**
 * The switch element parser.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class SwitchElementParser implements ElementParser {
    
    private ElementParser conditionParser;

    public ElementParser getConditionParser() {
        if (conditionParser == null) {
            conditionParser = new ConditionParser();
        }
        return conditionParser;
    }

    public void setConditionParser(ElementParser conditionParser) {
        this.conditionParser = conditionParser;
    }

    // ---------------------------------------------------------------------------------

    public boolean canParse(Node node) {
        return node != null && node.getNodeName().equals("switch");
    }

    @SuppressWarnings(Warnings.Unchecked)
    public void parse(Node node, ConfigurationBuilder builder) throws ParseException {
        ArgumentAssert.isNotNull(node, "Node can't be null.");
        ArgumentAssert.isNotNull(builder, "Configuration builder can't be null.");

        final String name = ParserUtil.parseAttribute(node, "name", true);

        final SwitchElement switchElement = new SwitchElement(name);
        builder.addSwitch(switchElement);

        builder.getBuffer().clear();
        ParserUtil.parseChildren(builder, node, ListUtils.listOf(getConditionParser()));

        for (Condition each: builder.getBuffer().getConditions()) {
            switchElement.addCondition(each);
        }
        builder.getBuffer().clear();
    }
}
