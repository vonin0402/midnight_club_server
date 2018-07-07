package com.tag.midnight.club.server.util;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class DateTimeUtils {
    private static final DateTimeFormatter dttmFormatter = DateTimeFormat.forPattern("yyyyMMdd HHmmss SSS");
    private static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    public static String dttm(long timestamp) {
        return dttmFormatter.print(timestamp);
    }

    public static String today() {
        return dateFormatter.print(System.currentTimeMillis());
    }

    public static String date(long timestamp) {
        return dateFormatter.print(timestamp);
    }
}
