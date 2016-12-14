package com.jasongj.kafka.connect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.utils.AppInfoParser;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.source.SourceConnector;

public class ConsoleSourceConnect extends SourceConnector {

    private static final ConfigDef CONFIG_DEF = new ConfigDef().define("topic", Type.STRING, Importance.HIGH,
            "Target topic name");
    private static final String TOPIC_CONF_NAME = "topic";
    
    private String topic;

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

    @Override
    public void start(Map<String, String> props) {
        topic = props.get(TOPIC_CONF_NAME);
        if(StringUtils.isBlank(topic)) {
            throw new ConnectException("Topic must be configured");
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public Class<? extends Task> taskClass() {
        return ConsoleSourceTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        List<Map<String, String>> tasks = new ArrayList<Map<String, String>>();
        Map<String, String> task = new HashMap<String, String>();
        task.put(TOPIC_CONF_NAME, topic);
        tasks.add(task);
        return tasks;
    }

    @Override
    public String version() {
        return AppInfoParser.getVersion();
    }

}
