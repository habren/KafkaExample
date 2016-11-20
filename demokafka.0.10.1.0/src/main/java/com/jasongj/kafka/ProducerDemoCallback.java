package com.jasongj.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerDemoCallback {

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.put("bootstrap.servers", "kafka0:9092");
		props.put("acks", "all");
		props.put("retries", 3);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", StringSerializer.class.getName());
		props.put("value.serializer", StringSerializer.class.getName());
		props.put("partitioner.class", HashPartitioner.class.getName());

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		for (int i = 0; i < 10; i++) {
			ProducerRecord record = new ProducerRecord<String, String>("topic1", Integer.toString(i),
					Integer.toString(i));
//			producer.send(record);
//			producer.send(record, new Callback() {
//
//				@Override
//				public void onCompletion(RecordMetadata metadata, Exception exception) {
//					System.out.printf("Send record partition:%d, offset:%d, keysize:%d, valuesize:%d %n",
//							metadata.partition(), metadata.offset(), metadata.serializedKeySize(),
//							metadata.serializedValueSize());
//				}
//
//			});
			 producer.send(record, (metadata, exception) -> {
				 if(metadata != null) {
					 System.out.printf("Send record partition:%d, offset:%d, keysize:%d, valuesize:%d %n",
								metadata.partition(), metadata.offset(), metadata.serializedKeySize(),
								metadata.serializedValueSize());
				 }
				 if(exception != null) {
					 exception.printStackTrace();
				 }
			 });
		}
		producer.close();
	}

}
