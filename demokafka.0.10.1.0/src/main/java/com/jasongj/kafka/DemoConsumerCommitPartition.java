package com.jasongj.kafka;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;

public class DemoConsumerCommitPartition {

	public static void main(String[] args) throws Exception {
		args = new String[] { "kafka0:9092", "topic1", "group2", "consumer2" };
		if (args == null || args.length != 4) {
			System.err.println(
					"Usage:\n\tjava -jar kafka_consumer.jar ${bootstrap_server} ${topic_name} ${group_name} ${client_id}");
			System.exit(1);
		}
		String bootstrap = args[0];
		String topic = args[1];
		String groupid = args[2];
		String clientid = args[3];
		
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrap);
		props.put("group.id", groupid);
		props.put("enable.auto.commit", "false");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		props.put("max.poll.interval.ms", "300000");
		props.put("max.poll.records", "500");
		props.put("auto.offset.reset", "earliest");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(topic));
		AtomicLong atomicLong = new AtomicLong();
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			records.partitions().forEach(topicPartition -> {
				List<ConsumerRecord<String, String>> partitionRecords = records.records(topicPartition);
				partitionRecords.forEach(record -> {
					System.out.printf("client : %s , topic: %s , partition: %d , offset = %d, key = %s, value = %s%n", clientid, record.topic(),
							record.partition(), record.offset(), record.key(), record.value());
				});
				long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                consumer.commitSync(Collections.singletonMap(topicPartition, new OffsetAndMetadata(lastOffset + 1)));
			});
		}
	}

}
