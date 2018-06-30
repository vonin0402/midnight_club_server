package com.club.midnight.util;

import org.apache.commons.lang3.RandomStringUtils;

public abstract class RandomIdUtils {

    public static String generatePrefixedId(String prefix, int length) {
        return prefix + "_" + generateId(length);
    }

    public static String generateTimePrefixedId(int length) {
        return generatePrefixedId(DateTimeUtils.today(), length);
    }

    public static String generateId(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
