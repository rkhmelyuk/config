/*
 * Copyright (c) 2010 Ruslan Khmelyuk, Prutsoft
 * All rights reserved.
 *
 * Application configuration framework.
 */

package com.prutsoft.config.resource;

import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.utils.StringUtils;

import java.util.*;

/**
 * The registry of resources. Registry is used to get associate
 * resource with appropriate prefix. The same resource can be associated with
 * one and more prefix, but one prefix can be associated with only one resource.
 * <p/>
 * Prefix is just a string that ends with colon. For example,
 * path <code>file:/usr/local/app/conf/x.conf.xml</code> has prefix <code>file</code>
 * and path to the configuration <code>/usr/local/app/conf/x.conf.xml</code>.
 * <p/>
 * <table>
 * <tr>
 * <th> Prefix </th>
 * <th> Resource Class </th>
 * <th> Example </th>
 * </tr>
 * <tr>
 * <td> file </td>
 * <td> {@code com.prutsoft.config.resource.FileResource} </td>
 * <td> file:/usr/local/jboss/server/app/conf/appconfig.xml </td>
 * </tr>
 * <tr>
 * <td> classpath </td>
 * <td> {@code com.prutsoft.config.resource.ClasspathResource} </td>
 * <td> classpath:/com/myapp/config/razer.xml </td>
 * </tr>
 * <tr>
 * <td> string </td>
 * <td> {@code com.prutsoft.config.resource.StringResource} </td>
 * <td> string:&lt;configuration name="test" version="1.0.0"&gt;...&lt;/configuration&gt;</td>
 * </tr>
 * </table>
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2010-01-05
 */
public class ResourceRegistry {

    private static final String PREFIX_PATH_DELIMITER = ":";

    public static ResourceRegistry create() {
        ResourceRegistry registry = new ResourceRegistry();
        registry.putResource("string", StringResource.class);
        registry.putResource("file", FileResource.class);
        registry.putResource("classpath", ClasspathResource.class);
        return registry;
    }

    // ---------------------------------------------------------------

    private Map<String, Class<? extends Resource>> resources = new HashMap<String, Class<? extends Resource>>();

    /**
     * Adds prefix with appropriate resource class.
     * If such prefix existed before than resource class will be rewritten
     * and {@code false} returned. If such prefix is new than it will be added
     * and {@code true} returned.
     *
     * @param prefix        the prefix; can't be null or empty.
     * @param resourceClass the resource class; can't be null or empty.
     * @return {@code false} if such prefix existed before.
     */
    public boolean putResource(String prefix, Class<? extends Resource> resourceClass) {
        ArgumentAssert.isNotEmpty("prefix", "Prefix can't be null or empty.");
        ArgumentAssert.isNotNull(resourceClass, "Resource class can't be null.");

        return resources.put(prefix, resourceClass) == null;
    }

    /**
     * Removes resource with specified prefix.
     *
     * @param prefix the prefix; can't be null or empty.
     * @return {@code true} if resource was removed, otherwise {@code false}.
     */
    public boolean removePrefix(String prefix) {
        ArgumentAssert.isNotEmpty(prefix, "Prefix can't be null or empty.");

        return resources.remove(prefix) != null;
    }

    /**
     * Remove the specified resource.
     * If such resource was registered than it will be removed with all related prefixes.
     *
     * @param resource the resource to remove.
     * @return {@code true} if resource was removed, otherwise {@code false}.
     */
    public boolean removeResource(Class<? extends Resource> resource) {
        ArgumentAssert.isNotNull(resource, "Resource can't be null.");

        Collection<String> prefixes = getResourcePrefixes(resource);
        if (!prefixes.isEmpty()) {
            boolean removed = false;
            for (String each : prefixes) {
                removed |= removePrefix(each);
            }
            return removed;
        }
        return false;
    }

    /**
     * Gets resource class for specified prefix.
     *
     * @param prefix the prefix; can't be null or empty.
     * @return the resource class or {@code null} if it's not found.
     */
    public Class<? extends Resource> getResourceClass(String prefix) {
        ArgumentAssert.isNotEmpty(prefix, "Prefix can't be null or empty.");

        return resources.get(prefix);
    }

    /**
     * Gets the collection of all registered prefixes.
     *
     * @return the collection with registered prefixes.
     */
    public Collection<String> getPrefixes() {
        return Collections.unmodifiableCollection(resources.keySet());
    }

    /**
     * Checks whether specified prefix is registered.
     *
     * @param prefix the prefix.
     * @return {@code true} if prefix is registered already, otherwise {@code false}.
     */
    public boolean hasPrefix(String prefix) {
        return resources.containsKey(prefix);
    }

    /**
     * Checks whether specified resource class is registered.
     *
     * @param resourceClass the resource class.
     * @return {@code true} if class is registered already, otherwise {@code false}.
     */
    public boolean hasResource(Class<? extends Resource> resourceClass) {
        return resources.containsValue(resourceClass);
    }

    /**
     * Gets the collection of prefixes for specified resource class.
     *
     * @param resourceClass the resource class to get prefixes for; can't be null.
     * @return the collection with resource prefixes.
     */
    public Collection<String> getResourcePrefixes(Class<? extends Resource> resourceClass) {
        ArgumentAssert.isNotNull(resourceClass, "Resource class can't be null.");
        Collection<String> prefixes = new HashSet<String>();
        for (Map.Entry<String, Class<? extends Resource>> entry : resources.entrySet()) {
            if (entry.getValue().equals(resourceClass)) {
                prefixes.add(entry.getKey());
            }
        }
        return prefixes;
    }

    /**
     * Creates new resources for specified configuration name and returns it.
     *
     * @param configurationName the configuration name.
     * @return the resource for the configuration of {@code null}
     *         if appropriate prefix with resource were not found.
     * @throws ResourceLoadException error to instantiate resource.
     */
    public Resource create(String configurationName) throws ResourceLoadException {
        ArgumentAssert.isNotEmpty(configurationName, "Name can't be null or empty.");

        for (String eachPrefix : resources.keySet()) {
            // for each registered prefix we check whether it's used in the configuration name
            // and if used than create new instance of the resource
            final String path = StringUtils.getStringWithPrefixValue(
                    configurationName, eachPrefix + PREFIX_PATH_DELIMITER);
            if (path == null) continue;

            return newInstance(path, resources.get(eachPrefix));
        }

        // when prefix is not found
        return null;
    }

    private Resource newInstance(String path, Class<? extends Resource> clazz) throws ResourceLoadException {
        Resource result;
        try {
            // try to use default constructor
            result = clazz.getConstructor().newInstance();
        }
        catch (Exception e) {
            // if default constructor not exists or there is error to create new
            // resource instance than try to use constructor with 1 parameter - string...
            try {
                result = clazz.getConstructor(String.class).newInstance(path);
            }
            catch (Exception e2) {
                // ...and throw exception if that is impossible to instantiate with 1 parameter constructor
                throw new ResourceLoadException("Can't instantiate Resource instance for class " + clazz.getName());
            }
        }

        if (result == null) {
            // if resource is not created anyway - we throw exception
            throw new ResourceLoadException("Can't instantiate Resource instance for class " + clazz.getName());
        }

        return result;
    }
}
