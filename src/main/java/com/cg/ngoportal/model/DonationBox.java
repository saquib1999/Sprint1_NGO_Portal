package com.cg.ngoportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NDonationBox")
public class DonationBox {
	private String ngoName;
	
	@Id
	@GeneratedValue(generator = "NADMINID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NADMINID", sequenceName = "NADMIN_ID",allocationSize=1,initialValue = 1000)
	
	private int registrationNumber;
	private String accountNumber;
	private double totalCollection;
	
	public DonationBox() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DonationBox(String ngoName, String accountNumber, double totalCollection) {
		super();
		this.ngoName = ngoName;
		this.accountNumber = accountNumber;
		this.totalCollection = totalCollection;
	}
	public String getNgoName() {
		return ngoName;
	}
	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}
	
	public int getRegistrationNumber() {
		return registrationNumber;
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
