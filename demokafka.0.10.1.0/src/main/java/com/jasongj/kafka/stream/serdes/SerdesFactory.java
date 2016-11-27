package com.jasongj.kafka.stream.serdes;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

/**
 * This factory can create Serde for any POJO class <br/>
 * Be careful, the class should have a constructor without any arguments
 * and have setter and getter for every member variable
 * @author Jason Guo <habren@163.com>
 * 
 */

public class SerdesFactory {

	/**
	 * @param <T> The class should have a constructor without any
	 *        arguments and have setter and getter for every member variable
	 * @param pojoClass POJO class. 
	 * @return Instance of {@link Serde}
	 */
	public static <T> Serde<T> serdFrom(Class<T> pojoClass) {
		return Serdes.serdeFrom(new GenericSerializer<T>(pojoClass), new GenericDeserializer<T>(pojoClass));
	}

}
