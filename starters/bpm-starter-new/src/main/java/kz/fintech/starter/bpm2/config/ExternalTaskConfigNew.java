package kz.fintech.starter.bpm2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("camunda.external-task")
public class ExternalTaskConfigNew {
    private String baseUrl;
    private long responseTimeout;
}

