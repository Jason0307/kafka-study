package org.zhubao.spark.model;

public class Event {

	private String eventId;
	private String eventName;
	private long visitCount;

	
	public Event() {
		super();
	}

	public Event(String eventId, String eventName, long visitCount) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.visitCount = visitCount;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(long visitCount) {
		this.visitCount = visitCount;
	}

}
