package com.jasongj.kafka.stream;

import java.util.Optional;
import java.util.stream.Stream;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

public class DemoProcessor implements Processor<String, String> {

	private ProcessorContext context;
	private KeyValueStore<String, Integer> kvStore;

	@SuppressWarnings("unchecked")
	@Override
	public void init(ProcessorContext context) {
		this.context = context;
		this.context.schedule(1000);
		this.kvStore = (KeyValueStore<String, Integer>) context.getStateStore("Counts");
	}

	@Override
	public void process(String key, String value) {
		Stream.of(value.toLowerCase().split(" ")).forEach((String word) -> {
			Optional<Integer> counts = Optional.ofNullable(kvStore.get(word));
			kvStore.put(word, counts.map(count -> count + 1).orElse(1));
		});
	}

	@Override
	public void punctuate(long timestamp) {
		try (KeyValueIterator<String, Integer> iterator = this.kvStore.all()) {
			iterator.forEachRemaining(entry -> {
				context.forward(entry.key, entry.value);
				this.kvStore.delete(entry.key);
			});
		}
		context.commit();
	}

	@Override
	public void close() {
		this.kvStore.close();
	}

}
