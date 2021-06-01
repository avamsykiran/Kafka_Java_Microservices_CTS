package com.cts.kafka.rest.consumer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.kafka.rest.consumer.service.MessagingService;

@RestController
@RequestMapping("messages")
public class MessageController {

	private MessagingService msgService;
	
	@GetMapping("/receive")
	public ResponseEntity<List<String>> receive(){
		return ResponseEntity.ok(msgService.getMsgs());
	}
	
	@GetMapping("/receiveFiltered")
	public ResponseEntity<List<String>> receiveFiltered(){
		return ResponseEntity.ok(msgService.getMsgsFiltered());
	}
	
	@GetMapping("/receiveWithKey")
	public ResponseEntity<Map<String,List<String>>> receiveWihtKeys(){
		return ResponseEntity.ok(msgService.getMsgsMap());
	}
}
