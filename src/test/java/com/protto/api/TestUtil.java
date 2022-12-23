package com.protto.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJSON(Object obj) throws JsonProcessingException {
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> List<T> parseJson(String json, Class<T> type) throws JsonProcessingException {
        CollectionType listType =
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, type);
        if (json.charAt(0) != '[') {
            return List.of(objectMapper.readValue(json, type));
        }
        return objectMapper.readValue(json, listType);
    }

}
