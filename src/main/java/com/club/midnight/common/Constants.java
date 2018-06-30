package com.club.midnight.common;

import com.club.midnight.util.ExceptionLogUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public abstract class Constants {
    public static final String MASKED_ADID = "00000000-0000-0000-0000-000000000000";
    public static final String HOST_NAME = getHostName();

    private static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            ExceptionLogUtils.error(log, e);
            return "UNKNOWN_HOST";
        }
    }
}
