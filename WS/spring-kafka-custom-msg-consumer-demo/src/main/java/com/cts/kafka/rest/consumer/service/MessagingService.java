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

import com.cts.kafka.rest.consumer.model.EventMessage;

@Service
public class MessagingService {

	private Logger logger;
	private Map<String, List<EventMessage>> msgsMap;

	public MessagingService() {
		this.logger = LoggerFactory.getLogger(this.getClass());
		this.msgsMap = new TreeMap<>();
	}

	@KafkaListener(topics = { "TopicEM" }, groupId = "emApp")
	public void receiveAlongWithHeaders(@Payload EventMessage message, 
			@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
			@Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		logger.info("Received " + key + ":" + message + " from partition#" + partition);

		if (msgsMap.containsKey(key)) {
			msgsMap.get(key).add(message);
		} else {
			List<EventMessage> msgs = new ArrayList<>();
			msgs.add(message);
			msgsMap.put(key, msgs);
		}
	}

	public Map<String, List<EventMessage>> getMsgsMap() {
		return msgsMap;
	}
	
}
