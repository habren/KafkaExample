package com.jasongj.kafka.connect;

import java.util.Collection;
import java.util.Map;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

public class ConsoleSinkTask extends SinkTask {

    @Override
    public String version() {
        return null;
    }

    @Override
    public void flush(Map<TopicPartition, OffsetAndMetadata> arg0) {

    }

    @Override
    public void put(Collection<SinkRecord> arg0) {

    }

    @Override
    public void start(Map<String, String> arg0) {

    }

    @Override
    public void stop() {

    }

}
