package com.cts.kafka.rest.producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.kafka.rest.producer.service.MessagingService;

@RestController
@RequestMapping("messages")
public class MessageController {

	private MessagingService msgService;
	
	@PostMapping("/send/{:key}")
	public ResponseEntity<Void> send(@PathVariable("key") String key,@RequestBody String msg){
		msgService.send(key, msg);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
