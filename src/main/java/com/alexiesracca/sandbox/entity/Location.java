package com.alexiesracca.sandbox.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "location")
public class Location extends Entity {
	
	public enum LocationCategory{
		STRUCTURE, VEHICLE, PERSON, GROUP, LANDMARK, OTHERS
	}

	@Id
	private String id;
	private String subject;
	private LocationCategory category;
	private GeoJsonPoint location;

	public Location() {

	}
	
	public Location(final String subject, final GeoJsonPoint location) {
		this.subject = subject;
		this.location = location;
		this.category = LocationCategory.OTHERS;
	}
	
	public Location(final String subject, final GeoJsonPoint location, final LocationCategory category) {
		this.subject = subject;
		this.location = location;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public GeoJsonPoint getLocation() {
		return location;
	}

	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}

	public LocationCategory getCategory() {
		return category;
	}

	public void setCategory(LocationCategory category) {
		this.category = category;
	}



}


