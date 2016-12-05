package com.jasongj.kafka.connect;

import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

public class ConsoleSourceConnect extends SinkConnector {

    @Override
    public ConfigDef config() {
        return null;
    }

    @Override
    public void start(Map<String, String> arg0) {
        
    }

    @Override
    public void stop() {
        
    }

    @Override
    public Class<? extends Task> taskClass() {
        return null;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int arg0) {
        return null;
    }

    @Override
    public String version() {
        return null;
    }


}
