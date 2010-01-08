package com.prutsoft.config.element.value;

import com.prutsoft.core.annotation.NotNull;
import com.prutsoft.core.asserts.ArgumentAssert;

import java.util.HashMap;
import java.util.Map;

/**
 * The enumeration of supported types.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-02
 */
public class ValueTypeRegistry {

    private static final String STRING_VALUE_TYPE = "string";

    /**
     * The map of value types. There could be only one value type for specified type name.
     * There could be few type names for the same value type.
     */
    private static final Map<String, ValueType> VALUE_TYPES;

    static {
        VALUE_TYPES = new HashMap<String, ValueType>();

        registerDefaultValueTypes();
    }

    /**
     * Registers default value types.
     */
    private static void registerDefaultValueTypes() {
        registerValueType(STRING_VALUE_TYPE, new StringValueType());
        registerValueType("integer", new IntegerValueType());
        registerValueType("float", new FloatValueType());
        registerValueType("double", new DoubleValueType());
        registerValueType("list", new ListValueType());
        registerValueType("set", new SetValueType());
        registerValueType("boolean", new BooleanValueType());
        registerValueType("long", new LongValueType());
        registerValueType("dict", new DictionaryValueType());

        addAlias("String", STRING_VALUE_TYPE);
        addAlias("int", "integer");
        addAlias("Integer", "integer");
        addAlias("dictionary", "dict");
        addAlias("Dictionary", "dict");
    }

    /**
     * Registers new value type.
     * If value type with such name exists already, than don't override it, but return {@code false}.
     * Otherwise add new value type and return {@code true}.
     *
     * @param name the name of value type; can't be null or empty.
     * @param valueType the value type; can't be null.
     * @return {@code true} if value type is registered successfully, otherwise {@code false}.
     */
    public static boolean registerValueType(@NotNull String name, @NotNull ValueType valueType) {
        ArgumentAssert.isNotEmpty(name, "Name is required.");
        ArgumentAssert.isNotNull(valueType, "Value type can't be null.");

        if (!VALUE_TYPES.containsKey(name)) {
            VALUE_TYPES.put(name, valueType);
            return true;
        }

        return false;
    }

    /**
     * Removes value type with specified name.
     * If value type with such name exists already, than remove it and return {@code true}.
     * Otherwise return {@code false}.
     *
     * @param name the name of value type; can't be null or empty.
     * @return {@code true} if value type is removed successfully, otherwise {@code false}.
     */
    public static boolean removeValueType(@NotNull String name) {
        ArgumentAssert.isNotEmpty(name, "Name is required.");
        return VALUE_TYPES.remove(name) != null;
    }

    /**
     * Adds the alias for specified value type name.
     * If value type name is not exist yet, than returns {@code false}.
     * If such alias or value type name like alias exist already than returns {@code false}.
     *
     * Returns {@code true} only when alias is created successfully.
     *
     * @param alias the alias to add; can't be null or empty.
     * @param valueTypeName the name of the value type to reference to; can't be null or empty.
     * @return {@code true} if alias was added successfully, otherwise returns {@code false}.
     */
    public static boolean addAlias(@NotNull String alias, @NotNull String valueTypeName) {
        ArgumentAssert.isNotEmpty(alias, "Alias is required");
        ArgumentAssert.isNotEmpty(valueTypeName, "Value type name is required");

        if (VALUE_TYPES.containsKey(alias)
                && !VALUE_TYPES.containsKey(valueTypeName)) {
            return false;
        }

        VALUE_TYPES.put(alias, VALUE_TYPES.get(valueTypeName));
        return true;
    }

    /**
     * Gets the value type by it's name or alias.
     * If value type is nto found, than will be returned default one, e.g. String value type.
     *
     * @param name the name or alias of the value type.
     * @return the value type for specified name.
     */
    public static ValueType getValueType(String name) {
        return name != null ? VALUE_TYPES.get(name)
                : VALUE_TYPES.get(STRING_VALUE_TYPE);
    }
}
