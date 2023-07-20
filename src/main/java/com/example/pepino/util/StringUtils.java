package com.example.pepino.util;

public abstract class StringUtils {

    /**
     * Check whether the string is null or if it is empty or blank (contains no
     * characters at all or only blank spaces).
     * 
     * @param value string to check.
     * @return returns true if empty or false otherwise.
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * Check whether the string is not null but it is blank (contains no characters
     * at all or only blank spaces).
     * 
     * @param value string to check.
     * @return returns true if blank or false otherwise.
     */
    public static boolean isBlank(String value) {
        return value != null && value.trim().length() == 0;
    }
}
