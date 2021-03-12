package com.cg.ngoportal.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NDonationBox")
public class DonationBox {
	private String ngoName;
	@Id
	private String registrationNumber;
	private String accountNumber;
	private double totalCollection;
	
	public DonationBox() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DonationBox(String ngoName, String registrationNumber, String accountNumber, double totalCollection) {
		super();
		this.ngoName = ngoName;
		this.registrationNumber = registrationNumber;
		this.accountNumber = accountNumber;
		this.totalCollection = totalCollection;
	}
	public String getNgoName() {
		return ngoName;
	}
	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getTotalCollection() {
		return totalCollection;
	}
	public void setTotalCollection(double totalCollection) {
		this.totalCollection = totalCollection;
	}
	@Override
	public String toString() {
		return "DonationBox [ngoName=" + ngoName + ", registrationNumber=" + registrationNumber + ", accountNumber="
				+ accountNumber + ", totalCollection=" + totalCollection + "]";
	}
	
	
}
