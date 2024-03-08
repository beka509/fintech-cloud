package kz.fintech.starter.bpm2.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "camunda.external-task")
public class BpmPropsNew {
    private String baseUrl;
    private String eurekaBaseUrl;
    private long responseTimeout;
    private int maxTasks;
    private int maxThreads;
    private Map<String, ProcessProps> processes;

    @Data
    public static class ProcessProps {
        private int maxTasks;
        private int maxThreads;
    }
}