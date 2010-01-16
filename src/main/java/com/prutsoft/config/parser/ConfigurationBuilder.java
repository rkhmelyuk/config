/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.parser;

import com.prutsoft.config.Configuration;
import com.prutsoft.config.ConfigurationImpl;
import com.prutsoft.config.NamedElement;
import com.prutsoft.config.Version;
import com.prutsoft.config.element.control.SwitchElement;
import com.prutsoft.config.element.expression.ExpressionElement;
import com.prutsoft.config.element.metadata.Metadata;
import com.prutsoft.config.element.metadata.MetadataProperty;
import com.prutsoft.config.element.pojo.PojoElement;
import com.prutsoft.config.element.property.Property;
import com.prutsoft.config.element.reference.Reference;
import com.prutsoft.config.element.reload.ReloadPolicy;
import com.prutsoft.config.element.set.PropertySet;
import com.prutsoft.config.element.value.Value;
import com.prutsoft.config.exception.ConfigurationException;
import com.prutsoft.config.exception.DuplicateElementException;
import com.prutsoft.core.asserts.ArgumentAssert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Use to build configuration. Provides usable methods to build configuration
 * object.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-06
 */
public class ConfigurationBuilder {

    private String name;
    private Version version;
    private ReloadPolicy reloadPolicy;

    private List<Property> properties = new ArrayList<Property>();
    private List<PropertySet> sets = new ArrayList<PropertySet>();
    private List<PojoElement> pojos = new ArrayList<PojoElement>();
    private List<SwitchElement> switches = new ArrayList<SwitchElement>();
    private List<ExpressionElement> expressions = new ArrayList<ExpressionElement>();
    private List<MetadataProperty> metadataProperties = new ArrayList<MetadataProperty>();
    private List<Reference> references = new ArrayList<Reference>();

    private List<String> configurationsPaths = new ArrayList<String>();
    private List<Configuration> configurations = new ArrayList<Configuration>();

    private final BuilderBuffer buffer = new BuilderBuffer();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public void setVersion(String version) {
        this.version = Version.parse(version);
    }

    public ReloadPolicy getReloadPolicy() {
        return reloadPolicy;
    }

    public void setReloadPolicy(ReloadPolicy reloadPolicy) {
        this.reloadPolicy = reloadPolicy;
    }

    public BuilderBuffer getBuffer() {
        return buffer;
    }

    public boolean addConfigurationPath(String configuration) {
        ArgumentAssert.isNotEmpty(configuration, "Configuration can't be null.");
        return configurationsPaths.add(configuration);
    }

    public boolean removeConfigurationPath(String configuration) {
        return configurationsPaths.remove(configuration);
    }

    public List<String> getConfigurationPaths() {
        return configurationsPaths;
    }

    public boolean addConfiguration(Configuration configuration) {
        ArgumentAssert.isNotNull(configuration, "Configuration can't be null.");
        return configurations.add(configuration);
    }

    public boolean removeConfiguration(Configuration configuration) {
        return configurations.remove(configuration);
    }

    public boolean addProperty(Property property) {
        ArgumentAssert.isNotNull(property, "Property can't be null.");
        return properties.add(property);
    }

    public Property getProperty(String name) {
        for (Property each : properties) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        return null;
    }

    public boolean setPropertyValue(String name, Value value) {
        ArgumentAssert.isNotNull(value, "Value can't be null.");
        Property property = getProperty(name);
        if (property != null) {
            property.setValue(value);
            return true;
        }
        return false;
    }

    public boolean addReference(Reference reference) {
        ArgumentAssert.isNotNull(reference, "Property can't be null.");
        return references.add(reference);
    }

    public Reference getReference(String name) {
        for (Reference each : references) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        return null;
    }

    public boolean addSet(PropertySet set) {
        ArgumentAssert.isNotNull(set, "Set can't be null.");
        return sets.add(set);
    }

    public PropertySet getPropertySet(String name) {
        for (PropertySet each : sets) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        return null;
    }

    public boolean addToPropertySet(String setName, Property property) {
        ArgumentAssert.isNotNull(property, "Property can't be null.");
        PropertySet set = getPropertySet(setName);
        if (set != null) {
            return set.addElement(property);
        }
        return false;
    }

    public boolean addPojo(PojoElement pojo) {
        ArgumentAssert.isNotNull(pojo, "Pojo can't be null.");
        return pojos.add(pojo);
    }

    public PojoElement getPojo(String name) {
        for (PojoElement each : pojos) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        return null;
    }


    public void addSwitch(SwitchElement switchElement) {
        ArgumentAssert.isNotNull(switchElement, "Switch element can't be null.");
        switches.add(switchElement);
    }

    public SwitchElement getSwitch(String name) {
        for (SwitchElement each : switches) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        return null;
    }

    public void addExpression(ExpressionElement expressionElement) {
        ArgumentAssert.isNotNull(expressionElement, "Expression element can't be null.");
        expressions.add(expressionElement);
    }

    public ExpressionElement getExpression(String name) {
        for (ExpressionElement each : expressions) {
            if (each.getName().equals(name)) {
                return each;
            }
        }
        return null;
    }

    public boolean addMetadataProperty(MetadataProperty property) {
        ArgumentAssert.isNotNull(property, "Metadata Property can't be null.");
        return metadataProperties.add(property);
    }

    public boolean addMetadataProperty(String name, String value) {
        ArgumentAssert.isNotEmpty(name, "Name can't be null or empty.");
        return metadataProperties.add(new MetadataProperty(name, value));
    }

    public List<Property> getProperties() {
        return Collections.unmodifiableList(properties);
    }

    public List<PropertySet> getSets() {
        return Collections.unmodifiableList(sets);
    }

    public List<PojoElement> getPojos() {
        return Collections.unmodifiableList(pojos);
    }

    public List<SwitchElement> getSwitches() {
        return Collections.unmodifiableList(switches);
    }

    public List<ExpressionElement> getExpressions() {
        return Collections.unmodifiableList(expressions);
    }

    public List<Reference> getReferences() {
        return Collections.unmodifiableList(references);
    }

    public List<MetadataProperty> getMetadataProperties() {
        return Collections.unmodifiableList(metadataProperties);
    }

    public Configuration toConfiguration() throws ConfigurationException {
        final ConfigurationImpl config = new ConfigurationImpl(name, version);

        config.setReloadPolicy(reloadPolicy);

        addMetadata(config);
        addConfigurations(config);

        addSets(config);
        addSwitches(config);
        addProperties(config);
        addExpressions(config);
        addPojos(config);
        addReferences(config);

        return config;
    }

    private void addConfigurations(ConfigurationImpl config) {
        config.getIncludedConfigurations().addAll(configurations);
    }

    private void addProperties(ConfigurationImpl config) throws ConfigurationException {
        for (NamedElement each : properties) {
            addNamedElement(config, each);
        }
    }

    private void addReferences(ConfigurationImpl config) throws ConfigurationException {
        for (NamedElement each : references) {
            addNamedElement(config, each);
        }
    }

    private void addSwitches(ConfigurationImpl config) throws ConfigurationException {
        for (NamedElement each : switches) {
            addNamedElement(config, each);
        }
    }

    private void addExpressions(ConfigurationImpl config) throws ConfigurationException {
        for (NamedElement each : expressions) {
            addNamedElement(config, each);
        }
    }

    private void addSets(ConfigurationImpl config) throws ConfigurationException {
        for (NamedElement each : sets) {
            addNamedElement(config, each);
        }
    }

    private void addPojos(ConfigurationImpl config) throws ConfigurationException {
        for (NamedElement each : pojos) {
            addNamedElement(config, each);
        }
    }

    private void addNamedElement(ConfigurationImpl config, NamedElement each) throws DuplicateElementException {
        boolean added = config.addElement(each);
        if (!added) {
            throw new DuplicateElementException("Configuration element with name ["
                    + each.getName() + "] exists already.");
        }
    }

    private void addMetadata(ConfigurationImpl c) throws ConfigurationException {
        final Metadata metadata = new Metadata();
        for (MetadataProperty each : metadataProperties) {
            boolean added = metadata.addProperty(each);
            if (!added) {
                throw new DuplicateElementException("Metadata property with name ["
                        + each.getName() + "] exists already.");
            }
        }
        c.setMetadata(metadata);
    }
}
