package com.study.spring.Json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class ObjectMapperFactory {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
//		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
//				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
//				.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//				.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
//				.registerModule(new BirdModule());
		OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	public static ObjectMapper getDefaultObjectMapper() {		
		return OBJECT_MAPPER.copy();
	}

}
