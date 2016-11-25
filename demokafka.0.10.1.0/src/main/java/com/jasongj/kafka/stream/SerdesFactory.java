package com.jasongj.kafka.stream;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class SerdesFactory {

	public static <T> Serde<T> serdFrom(Class<T> typeClass) {
		return Serdes.serdeFrom(new GenericSerializer<T>(typeClass), new GenericDeserializer<T>(typeClass));
	}

}
