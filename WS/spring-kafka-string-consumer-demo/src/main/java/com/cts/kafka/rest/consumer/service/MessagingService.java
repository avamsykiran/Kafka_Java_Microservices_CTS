package com.cts.kafka.rest.consumer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

	private Logger logger;
	private List<String> msgs;
	private List<String> msgsFiltered;
	private Map<String, List<String>> msgsMap;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public MessagingService() {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.msgs = new ArrayList<>();
		this.msgsFiltered = new ArrayList<>();
		this.msgsMap = new TreeMap<>();
	}

	@KafkaListener(topics = { "TopicA" }, groupId = "skc")
	public void receive(String message) {
		logger.info("Received " + message);
		msgs.add(message);
	}

	@KafkaListener(topics = { "TopicA" }, groupId = "skc", 
			containerFactory = "filteredKafkaListenerContainerFactory")
	public void receiveFileredMessages(String message) {
		logger.info("Received " + message);
		msgsFiltered.add(message);
	}

	@KafkaListener(topics = { "TopicA" }, groupId = "skc")
	public void receiveAlongWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		logger.info("Received " + key + ":" + message + " from partition#" + partition);

		if (msgsMap.containsKey(key)) {
			msgsMap.get(key).add(message);
		} else {
			List<String> msgs = new ArrayList<>();
			msgs.add(message);
			msgsMap.put(key, msgs);
		}
	}

	public List<String> getMsgs() {
		return msgs;
	}

	public List<String> getMsgsFiltered() {
		return msgsFiltered;
	}

	public Map<String, List<String>> getMsgsMap() {
		return msgsMap;
	}

	
}
