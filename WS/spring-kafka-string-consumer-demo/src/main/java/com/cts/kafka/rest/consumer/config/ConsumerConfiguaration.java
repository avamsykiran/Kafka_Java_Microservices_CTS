package com.cts.kafka.rest.consumer.config;

import java.util.Map;
import java.util.TreeMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
public class ConsumerConfiguaration {

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> configs = new TreeMap<>();

		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory kfc = new ConcurrentKafkaListenerContainerFactory<>();
		kfc.setConsumerFactory(consumerFactory());
		return kfc;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> filteredKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory kfc = new ConcurrentKafkaListenerContainerFactory<>();
		kfc.setConsumerFactory(consumerFactory());
		//kfc.setRecordFilterStrategy(record -> record.key().equals("Key5"));
		//kfc.setRecordFilterStrategy(record -> record.partition()>=3 && record.partition()<=8);
		kfc.setRecordFilterStrategy(record -> ((String)record.value()).startsWith("insert"));
		return kfc;
	}
}
