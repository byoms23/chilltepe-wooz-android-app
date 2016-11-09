package me.wooz.mobile.android.dto.ensurance;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by byron on 8/11/16.
 */

public class InsuranceType {

	private Integer id;

	private String name;

	private String description;

	@JsonProperty("created_at")
	private DateTime createdAt;

	@JsonProperty("updated_at")
	private DateTime updatedAt;

	@JsonProperty("event_type")
	private List<EventType> eventsTypes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public DateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<EventType> getEventsTypes() {
		return eventsTypes;
	}

	public void setEventsTypes(List<EventType> eventsTypes) {
		this.eventsTypes = eventsTypes;
	}

	@Override
	public String toString() {
		return name;
	}
}
