package com.d2c.notification.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private JsonUtil() {
		super();
	}

	public static String objectToJson(Object object, boolean isRemoveNull) {
		try {
			ObjectMapper objMapper = new ObjectMapper();
			if (isRemoveNull) {
				objMapper.setSerializationInclusion(Include.NON_NULL);
			}
			return objMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "Unbale to parse in json";
		}
	}
}
