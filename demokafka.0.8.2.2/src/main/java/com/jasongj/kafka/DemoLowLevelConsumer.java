package com.jasongj.kafka;

import java.nio.ByteBuffer;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

public class DemoLowLevelConsumer {

	public static void main(String[] args) throws Exception {
		final String topic = "topic1";
		String clientID = "DemoLowLevelConsumer1";
		SimpleConsumer simpleConsumer = new SimpleConsumer("kafka0", 9092, 100000, 64 * 1000000, clientID);
		FetchRequest req = new FetchRequestBuilder().clientId(clientID)
				.addFetch(topic, 0, 0L, 50).addFetch(topic, 1, 0L, 5000).addFetch(topic, 2, 0L, 1000000).build();
		FetchResponse fetchResponse = simpleConsumer.fetch(req);
		ByteBufferMessageSet messageSet = (ByteBufferMessageSet) fetchResponse.messageSet(topic, 0);
		for (MessageAndOffset messageAndOffset : messageSet) {
			ByteBuffer payload = messageAndOffset.message().payload();
			long offset = messageAndOffset.offset();
			byte[] bytes = new byte[payload.limit()];
			payload.get(bytes);
			System.out.println("Offset:" + offset + ", Payload:" + new String(bytes, "UTF-8"));
		}
	}

}
