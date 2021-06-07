package com.cts.kafka.rest.consumer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.kafka.rest.consumer.model.EventMessage;
import com.cts.kafka.rest.consumer.service.MessagingService;

@RestController
@RequestMapping("messages")
public class MessageController {

	private MessagingService msgService;
	
	@GetMapping("/receiveWithKey")
	public ResponseEntity<Map<String,List<EventMessage>>> receiveWihtKeys(){
		return ResponseEntity.ok(msgService.getMsgsMap());
	}
}
