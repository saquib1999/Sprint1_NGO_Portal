package com.cg.ngoportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NDonor")
public class Donor {
	@Id
	private int donorId;
	private String donorName;
	private String donorEmail;
	private String donorPhone;
	private String donorUsername;
	private String donorPassword;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
	private Address address;

	
	public Donor() {
		// TODO Auto-generated constructor stub
	}
	
	public Donor(int donorId, String donorName, String donorEmail, String donorPhone, String donorUsername,
			String donorPassword, Address address) {
		super();
		this.donorId = donorId;
		this.donorName = donorName;
		this.donorEmail = donorEmail;
		this.donorPhone = donorPhone;
		this.donorUsername = donorUsername;
		this.donorPassword = donorPassword;
		this.address = address;
	}
	public int getDonorId() {
		return donorId;
	}
	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	public String getDonorName() {
		return donorName;
	}
	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}
	public String getDonorEmail() {
		return donorEmail;
	}
	public void setDonorEmail(String donorEmail) {
		this.donorEmail = donorEmail;
	}
	public String getDonorPhone() {
		return donorPhone;
	}
	public void setDonorPhone(String donorPhone) {
		this.donorPhone = donorPhone;
	}
	public String getDonorUsername() {
		return donorUsername;
	}
	public void setDonorUsername(String donorUsername) {
		this.donorUsername = donorUsername;
	}
	public String getDonorPassword() {
		return donorPassword;
	}
	public void setDonorPassword(String donorPassword) {
		this.donorPassword = donorPassword;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Donor [donorId=" + donorId + ", donorName=" + donorName + ", donorEmail=" + donorEmail + ", donorPhone="
				+ donorPhone + ", donorUsername=" + donorUsername + ", donorPassword=" + donorPassword + ", address="
				+ address + "]";
	}
	
	
}
