package com.jasongj.kafka.connect;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Map;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

public class ConsoleSinkTask extends SinkTask {

    private PrintStream printStream;

    @Override
    public String version() {
        return AppInfoParser.getVersion();
    }

    @Override
    public void flush(Map<TopicPartition, OffsetAndMetadata> offsets) {
        if(printStream != null){
            printStream.flush();
        }
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        records.forEach(printStream::println);
    }

    @Override
    public void start(Map<String, String> config) {
        this.printStream = System.out;
    }

    @Override
    public void stop() {
        if(printStream != null){
            printStream.close();
        }

    }

}
