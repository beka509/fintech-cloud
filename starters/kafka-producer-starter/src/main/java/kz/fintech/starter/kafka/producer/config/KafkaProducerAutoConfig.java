package kz.fintech.starter.kafka.producer.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Import(KafkaProducerProps.class)
@Configuration
@RequiredArgsConstructor
public class KafkaProducerAutoConfig {
    private final KafkaProducerProps kafkaProducerProps;

    @Bean
    public ProducerFactory<String, String> kafkaProducerFactory() {
        val configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProps.getBootstrapAddress());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    @DependsOn("kafkaProducerFactory")
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(kafkaProducerFactory());
    }
}