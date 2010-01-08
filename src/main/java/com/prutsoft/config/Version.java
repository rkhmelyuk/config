package com.prutsoft.config;

import com.prutsoft.core.asserts.ArgumentAssert;
import com.prutsoft.core.utils.ObjectUtils;

import java.io.Serializable;

/**
 * Represents the configuration version number.
 * Version type is unmodifiable and serializable.
 *
 * @author Ruslan Khmelyuk
 * @since 1.0.0, 2009-12-20
 */
public class Version implements Serializable {

    /**
     * Parses the string that contains version number.
     *
     * @param versionNumber the version number; can't be null or empty.
     * @return the Version object for specified version number.
     */
    public static Version parse(String versionNumber) {
        ArgumentAssert.isNotEmpty(versionNumber, "Version number is required.");
        
        final String[] arr = versionNumber.split("\\.");
        if (arr.length != 3) {
            throw new IllegalStateException("Wrong version number format: expected x.x.x");
        }
        return new Version(arr[2], arr[0], arr[1]);
    }

    // ----------------------------------------------------------------------

    /**
     * The version number.
     */
    private String name;

    /**
     * The version major number.
     */
    private String major;

    /**
     * The version minor number.
     */
    private String minor;

    /**
     * Constructs new object with specified name, major and minor version.
     * 
     * @param name the version name.
     * @param major the major version.
     * @param minor the minor version.
     */
    public Version(String name, String major, String minor) {
        this.name = name;
        this.major = major;
        this.minor = minor;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    // ----------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Version)) return false;

        final Version other = (Version) o;
        return ObjectUtils.equals(name, other.name) &&
                ObjectUtils.equals(major, other.major) &&
                ObjectUtils.equals(minor, other.minor);
    }

    @Override
    public String toString() {
        return new StringBuilder(50).append("Version[")
                .append("name='").append(name)
                .append("', major='").append(major)
                .append("', minor='").append(minor)
                .append("']").toString();
    }
}
