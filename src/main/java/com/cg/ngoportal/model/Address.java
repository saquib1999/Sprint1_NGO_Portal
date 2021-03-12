package com.cg.ngoportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NAddress")
public class Address {
	@Id
    @GeneratedValue(generator = "NADD_ID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NADD_ID", sequenceName = "NADDID",allocationSize=1, initialValue = 1000)	
	private int addressId;
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


	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
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
		return "Address [addressId=" + addressId + ", city=" + city + ", state=" + state + ", pin=" + pin
				+ ", landmark=" + landmark + "]";
	}
	
	
	
	
}
