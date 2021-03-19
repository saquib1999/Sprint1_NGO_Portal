package com.cg.ngoportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NDonor")
public class Donor {
	@Id
	@GeneratedValue(generator = "NDONORID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NDONORID", sequenceName = "NDONOR_ID",allocationSize=1,initialValue = 1000)
	
	private int id;
	private String name;
	private String email;
	private String phone;
	@OneToOne
	private User userLoginDetails;
	private Address address;

	
	public Donor() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Donor(String name, String email, String phone, User userLoginDetails, Address address) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.userLoginDetails = userLoginDetails;
		this.address = address;
	}


	
	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public User getUserLoginDetails() {
		return userLoginDetails;
	}


	public void setUserLoginDetails(User userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Donor [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", userLoginDetails="
				+ userLoginDetails + ", address=" + address + "]";
	}


	
	
}
