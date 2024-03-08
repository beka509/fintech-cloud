package kz.fintech.starter.kafka.consumer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("kafka.consumer")
public class KafkaConsumerProps {
    private String bootstrapAddress;
    private String groupId;
}