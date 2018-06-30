package com.club.midnight.util;

import com.club.midnight.util.exception.JsonParsingException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public abstract class JsonUtils {
    public static final String DEFAULT_FILTER = "filter";

    private static final ObjectMapper om = new ObjectMapper()
            .setSerializationInclusion(Include.NON_NULL)
            .setFilterProvider(new SimpleFilterProvider().addFilter(JsonUtils.DEFAULT_FILTER, SimpleBeanPropertyFilter.serializeAll()))
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static String toJson(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (Exception e) {
            ExceptionLogUtils.error(log, e);
            return "{}";
        }
    }

    public static String toJson(ObjectMapper om, Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (Exception e) {
            ExceptionLogUtils.error(log, e);
            return "{}";
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) throws JsonParsingException {
        try {
            return om.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonParsingException(clazz, e);
        }
    }

    public static <T> T fromJson(ObjectMapper om, String json, Class<T> clazz) throws JsonParsingException {
        try {
            return om.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonParsingException(clazz, e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) throws JsonParsingException {
        try {
            return om.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonParsingException(typeReference, e);
        }
    }


    public static String prettify(String json) {
        try {
            return toPrettyJson(fromJson(json, Object.class));
        } catch (Exception e) {
            ExceptionLogUtils.error(log, e);
            return "{}";
        }
    }

    public static String toPrettyJson(Object obj) {
        try {
            return om.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            ExceptionLogUtils.error(log, e);
            return "{}";
        }
    }
}
