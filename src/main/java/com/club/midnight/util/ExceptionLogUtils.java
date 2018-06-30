package com.club.midnight.util;

import org.slf4j.Logger;

/**
 * warn 및 error에 대한 모든 로깅을 담당
 */
public abstract class ExceptionLogUtils {

    public static void warn(Logger logger, Exception e) {
        logger.warn("{}(message={})", e.getClass().getSimpleName(), e.getMessage(), e);
    }

    public static void warn(Logger logger, Exception e, String format, Object arg1) {
        logger.warn("{}(message={}, " + format + ")", e.getClass().getSimpleName(), e.getMessage(), arg1, e);
    }

    public static void error(Logger logger, Exception e) {
        logger.error("{}(message={})", e.getClass().getSimpleName(), e.getMessage(), e);
    }

    public static void error(Logger logger, Exception e, String format, Object arg1) {
        logger.error("{}(message={}, " + format + ")", e.getClass().getSimpleName(), e.getMessage(), arg1, e);
    }
}