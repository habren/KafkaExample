package com.jasongj.kafka.stream;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericSerializer<T> implements Serializer<T> {

	private Class<T> type;
	private ObjectMapper objectMapper = new ObjectMapper();

	public GenericSerializer() {}
	
	public GenericSerializer(Class<T> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		if(type != null) {
			return;
		}
		String typeProp = isKey ? "key.serializer.type" : "value.serializer.type";
		String typeName = (String)configs.get(typeProp);
		try {
			type = (Class<T>)Class.forName(typeName);
		} catch (Exception ex) {
			throw new SerializationException("Failed to initialize GenericSerializer for " + typeName, ex);
		}
	}

	@Override
	public byte[] serialize(String topic, T object) {
		if (object == null) {
			return null;
		}
		try {
			return this.objectMapper.writerFor(type).writeValueAsBytes(object);
		} catch (IOException ex) {
			throw new SerializationException(ex);
		}
	}

	@Override
	public void close() {
	}

}