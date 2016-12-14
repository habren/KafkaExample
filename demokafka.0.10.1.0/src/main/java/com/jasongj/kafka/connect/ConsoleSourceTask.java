package com.jasongj.kafka.connect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.source.SourceTask;

public class ConsoleSourceTask extends SourceTask {

    private InputStream inputStream;
    private String topic;
    
    @Override
    public String version() {
        return null;
    }

    @Override
    public List<SourceRecord> poll() throws InterruptedException {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            List<SourceRecord> records = new ArrayList<SourceRecord>();
            while(reader.ready()){
                String line = reader.readLine();
//                SourceRecord record = new SourceRecord(null, null, topic, Schema.STRING_SCHEMA, line);
            }
            return records;
        } catch(IOException ex) {
            return null;
        }
    }

    @Override
    public void start(Map<String, String> conf) {
        inputStream = System.in;
        topic = conf.get("topic");
    }

    @Override
    public void stop() {
        
    }


}
