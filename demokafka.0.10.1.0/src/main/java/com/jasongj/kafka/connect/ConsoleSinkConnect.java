package com.jasongj.kafka.connect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

public class ConsoleSinkConnect extends SinkConnector {

    @Override
    public ConfigDef config() {
        return null;
    }

    @Override
    public void start(Map<String, String> config) {
    }

    @Override
    public void stop() {
        
    }

    @Override
    public Class<? extends Task> taskClass() {
        return ConsoleSinkTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        List<Map<String, String>> tasks = new ArrayList<Map<String, String>>();
        Map<String, String> task = new HashMap<String, String>();
        tasks.add(task);
        return tasks;
    }

    @Override
    public String version() {
        return AppInfoParser.getVersion();
    }


}
