package com.prutsoft.config.element.property;

import com.prutsoft.config.NamedElement;
import com.prutsoft.config.element.value.Value;
import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.utils.ObjectUtils;

/**
 * The regular configuration property.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-03
 */
public class Property implements NamedElement {

    private final String name;
    private Value value;
    private String description;

    public Property(String name) {
        ArgumentAssert.isNotEmpty(name, "Name can't be null or empty.");
        ArgumentAssert.isFalse(name.contains(":"), "Name can't contain colons.");
        
        this.name = name;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public Value getValue() {
        return value;
    }

    public void setValue(@NotNull Value value) {
        ArgumentAssert.isNotNull(value, "Value can't be null.");
        this.value = value;
    }

    /**
     * Gets property description.
     * @return the property description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets property description.
     * @param description the new property description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // -----------------------------------------------------------------

    public int hashCode() {
        return ObjectUtils.hashCode(name, value, description);
    }

    public boolean equals(Object o) {
        if (!(o instanceof Property)) return false;

        final Property other = (Property) o;

        return ObjectUtils.equals(name, other.name) &&
                ObjectUtils.equals(value, other.value) &&
                ObjectUtils.equals(description, other.description);
    }
}
