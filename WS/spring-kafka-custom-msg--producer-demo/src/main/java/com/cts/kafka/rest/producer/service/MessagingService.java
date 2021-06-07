package com.cts.kafka.rest.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cts.kafka.rest.producer.model.EventMessage;

@Service
public class MessagingService {

	private Logger logger;
	private String topic;

	@Autowired
	private KafkaTemplate<String, EventMessage> kafkaTemplate;

	public MessagingService() {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.topic = "TopicEM";
	}

	public void send(String key, EventMessage msg) {
		kafkaTemplate.send(topic, key, msg).addCallback((result) -> {
			logger.info("Message Send to Partiion: " + result.getProducerRecord().partition());
		}, (err) -> {
			logger.error(err.getMessage());
		});
	}
}
