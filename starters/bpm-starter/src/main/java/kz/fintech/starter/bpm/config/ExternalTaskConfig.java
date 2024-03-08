package kz.fintech.starter.bpm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("camunda.external-task")
public class ExternalTaskConfig {
    private String baseUrl;
    private long responseTimeout;
}

