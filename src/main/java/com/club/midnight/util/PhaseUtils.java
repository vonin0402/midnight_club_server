package com.club.midnight.util;

public abstract class PhaseUtils {
    private static final String ACTIVE_PROFILE = System.getProperty("spring.profiles.active");

    public static String getPhase() {
        return ACTIVE_PROFILE;
    }

    public static boolean isDevelop() {
        return "develop".equals(ACTIVE_PROFILE);
    }

    public static boolean isProduction() {
        return "production".equals(ACTIVE_PROFILE);
    }
}
