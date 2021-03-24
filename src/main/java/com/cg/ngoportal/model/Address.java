package com.cg.ngoportal.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String city;
	private String state;
	private String pin;
	private String landmark;
	
	
	public Address() {
		
	}
	
	
	public Address(String city, String state, String pin, String landmark) {
		super();
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.landmark = landmark;
	}


	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", pin=" + pin
				+ ", landmark=" + landmark + "]";
	}
	
	
	
	
}
