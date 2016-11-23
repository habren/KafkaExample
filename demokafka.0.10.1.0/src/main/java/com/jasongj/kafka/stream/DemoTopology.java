package com.jasongj.kafka.stream;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.TopologyBuilder;
import org.apache.kafka.streams.state.Stores;

public class DemoTopology {

	public static void main(String[] args) throws InterruptedException {
		Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount-processor");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:9092");
        props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "zookeeper0:2181");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.addSource("SOURCE", new StringDeserializer(), new StringDeserializer(), "kafka-source")
				.addProcessor("Processor1", DemoProcessor::new, "SOURCE")
				.addStateStore(Stores.create("Counts").withStringKeys().withIntegerValues().inMemory().build(), "Processor1")
				.connectProcessorAndStateStores("Processor1", "Counts")
				.addSink("SINK", "kafka-sink", new StringSerializer(), new IntegerSerializer(), "Processor1");
		
        KafkaStreams stream = new KafkaStreams(builder, props);
        stream.start();
        Thread.sleep(1500000L);
        stream.close();
        stream.cleanUp();
	}

}
