package com.cts.kafka.consumer.console.model;

import java.io.Serializable;

public class EventMessageModel implements Serializable {
	
	private long eventId;
	private String event;
	
	public EventMessageModel() {}

	public EventMessageModel(long evetnId, String event) {
		super();
		this.eventId = evetnId;
		this.event = event;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long evetnId) {
		this.eventId = evetnId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventMessageModel other = (EventMessageModel) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (eventId != other.eventId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventMessageModel [evetnId=" + eventId + ", event=" + event + "]";
	}
	
	

}