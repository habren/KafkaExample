package com.jasongj.kafka.consumer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class DemoConsumerFlowControl {

	public static void main(String[] args) {
		args = new String[] { "kafka0:9092", "topic1", "group239", "consumer2" };
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
		props.put("client.id", clientid);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		props.put("auto.offset.reset", "earliest");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener(){

			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
				partitions.forEach(topicPartition -> {
					System.out.printf("Revoked partition for client %s : %s-%s %n", clientid, topicPartition.topic(), topicPartition.partition());
				});
			}

			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
				partitions.forEach(topicPartition -> {
					System.out.printf("Assigned partition for client %s : %s-%s %n", clientid, topicPartition.topic(), topicPartition.partition());
				});
			}});
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100000000);
			consumer.pause(Arrays.asList(new TopicPartition(topic, 0)));
			consumer.pause(Arrays.asList(new TopicPartition(topic, 1)));
			records.forEach(record -> {
				System.out.printf("client : %s , topic: %s , partition: %d , offset = %d, key = %s, value = %s%n", clientid, record.topic(),
						record.partition(), record.offset(), record.key(), record.value());
			});
		}
	}

}
