/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser.xml;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.exception.ParseException;
import com.prutsoft.config.parser.ConfigurationBuilder;
import com.prutsoft.config.parser.ConfigurationParser;
import com.prutsoft.config.resource.Resource;
import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.asserts.ArgumentAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The parser for the configurations in XML format.
 * It's possible to replace default parsers or add own parsers in order
 * to introduce custom configuration elements.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 * 
 * @see com.prutsoft.config.Configuration
 * @see com.prutsoft.config.parser.ConfigurationParser
 * @see com.prutsoft.config.resource.Resource
 */
public class XmlConfigurationParser implements ConfigurationParser {

    private static final Logger log = LoggerFactory.getLogger(XmlConfigurationParser.class);

    private ElementParser metadataParser;
    private ElementParser reloadPolicyParser;
    private ElementParser propertyParser;
    private ElementParser expressionParser;
    private ElementParser switchParser;
    private ElementParser setParser;
    private ElementParser pojoParser;

    public ElementParser getMetadataParser() {
        if (metadataParser == null) {
            metadataParser = new MetadataParser();
        }
        return metadataParser;
    }

    public void setMetadataParser(ElementParser metadataParser) {
        this.metadataParser = metadataParser;
    }

    public ElementParser getReloadPolicyParser() {
        if (reloadPolicyParser == null) {
            reloadPolicyParser = new ReloadPolicyParser();
        }
        return reloadPolicyParser;
    }

    public void setReloadPolicyParser(ElementParser reloadPolicyParser) {
        this.reloadPolicyParser = reloadPolicyParser;
    }

    public ElementParser getPropertyParser() {
        if (propertyParser == null) {
            propertyParser = new PropertyParser();
        }
        return propertyParser;
    }

    public void setPropertyParser(ElementParser propertyParser) {
        this.propertyParser = propertyParser;
    }

    public ElementParser getExpressionParser() {
        if (expressionParser == null) {
            expressionParser = new ExpressionElementParser();
        }
        return expressionParser;
    }

    public void setExpressionParser(ElementParser expressionParser) {
        this.expressionParser = expressionParser;
    }

    public ElementParser getSwitchParser() {
        if (switchParser == null) {
            switchParser = new SwitchElementParser();
        }
        return switchParser;
    }

    public void setSwitchParser(ElementParser switchParser) {
        this.switchParser = switchParser;
    }

    public ElementParser getSetParser() {
        if (setParser == null) {
            setParser = new PropertySetParser();
        }
        return setParser;
    }

    public void setSetParser(ElementParser setParser) {
        this.setParser = setParser;
    }

    public ElementParser getPojoParser() {
        if (pojoParser == null) {
            pojoParser = new PojoParser();
        }
        return pojoParser;
    }

    public void setPojoParser(ElementParser pojoParser) {
        this.pojoParser = pojoParser;
    }

    // ----------------------------------------------------------------

    public Configuration parse(@NotNull Resource resource) throws ParseException {
        ArgumentAssert.isNotNull(resource, "Resource can't be null.");
        ArgumentAssert.isTrue(resource.exists(), "Resource must exist.");

        try {
            final ConfigurationBuilder builder = new ConfigurationBuilder();

            InputStream resourceStream = resource.getInputStream();

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(resourceStream);
            Node rootNode = document.getDocumentElement();

            loadConfiguration(rootNode, builder);

            return builder.toConfiguration();
        }
        catch (Exception e) {
            String msg = "Error to parse configuration " + resource.getName();
            log.error(msg + ", cause:", e);
            throw new ParseException(msg, e);
        }
    }

    private void loadConfiguration(Node node, ConfigurationBuilder builder) throws ParseException {
        if (!node.getNodeName().equals("configuration")) {
            throw new ParseException("<configuration> element is not found.");
        }
        builder.setName(node.getAttributes().getNamedItem("name").getNodeValue());
        builder.setVersion(node.getAttributes().getNamedItem("version").getNodeValue());

        ParserUtil.parseChildren(builder, node, getParsersList());
    }

    private List<ElementParser> getParsersList() {
        final List<ElementParser> parsers = new ArrayList<ElementParser>();
        parsers.add(getPropertyParser());
        parsers.add(getExpressionParser());
        parsers.add(getSetParser());
        parsers.add(getSwitchParser());
        parsers.add(getPojoParser());
        parsers.add(getMetadataParser());
        parsers.add(getReloadPolicyParser());
        return parsers;
    }

}
