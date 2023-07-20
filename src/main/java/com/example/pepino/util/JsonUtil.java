package com.example.pepino.util;

import java.io.IOException;
import java.io.InputStream;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object) {
        return buildJsonb().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> type) {
        return buildJsonb().fromJson(json, type);
    }

    public static <T> T fromJson(InputStream input, Class<T> type) {
        return buildJsonb().fromJson(input, type);
    }

    private static Jsonb buildJsonb() {
        JsonbConfig config = new JsonbConfig()
                .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES);
        return JsonbBuilder.create(config);
    }

    public static <T> T fromJsonWithJackson(String json, Class<T> type) throws JsonProcessingException {
        return mapper.readValue(json, type);
    }

    public static <T> T fromJsonWithJackson(InputStream input, Class<T> type) throws IOException {
        return mapper.readValue(input, type);
    }
}