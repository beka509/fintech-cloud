package kz.fintech.starter.kafka.producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("kafka.producer")
public class KafkaProducerProps {
    private String bootstrapAddress;
}