package dev.bengi.messageservice.config;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for Kafka.
 * This class sets up the Kafka producer and template beans.
 * @author S. Bengi
 */
@Configuration
public class KafkaConfig {

    /**
     * Creates a Kafka producer factory bean.
     * This factory is used to create Kafka producers.
     *
     * @return The producer factory bean.
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Creates a Kafka template bean.
     * This template is used to send messages to Kafka topics.
     *
     * @return The Kafka template bean.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        // Create the Kafka template using the producer factory
        return new KafkaTemplate<>(producerFactory());
    }
}