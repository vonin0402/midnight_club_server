package com.tag.midnight.club.server.util.exception;

import com.tag.midnight.club.server.common.exception.CustomException;
import com.fasterxml.jackson.core.type.TypeReference;

public class JsonParsingException extends CustomException {
    public JsonParsingException(Class<?> clazz, Exception e) {
        super(clazz.getCanonicalName() + " parsing exception: " + e.getMessage());
    }

    public JsonParsingException(TypeReference<?> typeReference, Exception e) {
        super(typeReference.getType() + " parsing exception: " + e.getMessage());
    }
}
