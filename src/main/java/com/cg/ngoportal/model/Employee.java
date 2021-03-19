package com.cg.ngoportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "NEmployee")
public class Employee {
	@Id 
	@GeneratedValue(generator = "NEMPID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "NEMPID", sequenceName = "NEMP_ID",allocationSize=1,initialValue = 100)
	private int id;
	private String name;
	private String email;
	private String phone;
	
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User userLoginDetails;
	
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
		}

	public Employee( String employeeName, String email, String phone, Address address, User userLoginDetails) {
		super();
		
		this.name = employeeName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.userLoginDetails = userLoginDetails;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUserLoginDetails() {
		return userLoginDetails;
	}

	public void setUserLoginDetails(User userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", userLoginDetails=" + userLoginDetails + "]";
	}
	
	
	
	
	
}
