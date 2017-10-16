package com.gazorpazorp.model;

import javax.persistence.Embedded;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Store {

	private Long id;
	private double latitude;
	private double longitude;
	@JsonAlias("address_line_1")
	private String address;
	private String city;
	@JsonAlias("postal_code")
	private String postalCode;
	
	@Embedded
	private Location location;
	
	
	public Store() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void Incorporate() {
		location = new Location();
		location.setAddress(this.address);
		location.setCity(this.city);
		location.setLatitude(this.latitude);
		location.setLongitude(this.longitude);
		location.setPostalCode(this.postalCode);
	}
	
	
	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}	
	
	
}
