package com.jasongj.kafka.stream;

import java.io.IOException;
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

public class WordCountTopology {

	public static void main(String[] args) throws IOException {
		Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount-processor");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka0:19092");
        props.put(StreamsConfig.ZOOKEEPER_CONNECT_CONFIG, "zookeeper0:12181/kafka");
        props.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		TopologyBuilder builder = new TopologyBuilder();
		builder.addSource("SOURCE", new StringDeserializer(), new StringDeserializer(), "words")
				.addProcessor("WordCountProcessor", WordCountProcessor::new, "SOURCE")
				.addStateStore(Stores.create("Counts").withStringKeys().withIntegerValues().inMemory().build(), "WordCountProcessor")
//				.connectProcessorAndStateStores("WordCountProcessor", "Counts")
				.addSink("SINK", "count", new StringSerializer(), new IntegerSerializer(), "WordCountProcessor");
		
        KafkaStreams stream = new KafkaStreams(builder, props);
        stream.start();
        System.in.read();
        stream.close();
        stream.cleanUp();
	}

}
