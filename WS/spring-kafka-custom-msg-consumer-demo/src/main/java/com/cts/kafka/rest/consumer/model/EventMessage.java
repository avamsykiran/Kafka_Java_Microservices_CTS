package com.cts.kafka.rest.consumer.model;

public class EventMessage {
	
	private String action;
	private String payload;
	public EventMessage() {
		
	}
	
	public EventMessage(String action, String payload) {
		super();
		this.action = action;
		this.payload = payload;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "EventMessage [action=" + action + ", payload=" + payload + "]";
	}
	
	
}