package com.cg.ngoportal.model;

import java.util.Date;

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
@Table(name = "NDonation")

public class Donation {
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "NDONATIONID", sequenceName = "NDONATION_ID",allocationSize=1,initialValue = 100)

	
	private int id;
	private int donorId;
	@OneToOne(cascade = CascadeType.ALL)
	private DonationItem item;
	private double amount;
	private Date dateOfDonation;
	
	
	
	
	

	public Donation(int donorId, DonationItem item, double amount, Date dateOfDonation) {
		super();
		this.donorId = donorId;
		this.item = item;
		this.amount = amount;
		this.dateOfDonation = dateOfDonation;
	}

	public Donation() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	

	

	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public DonationItem getItem() {
		return item;
	}

	public void setItem(DonationItem item) {
		this.item = item;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	

	public Date getDateOfDonation() {
		return dateOfDonation;
	}

	public void setDateOfDonation(Date dateOfDonation) {
		this.dateOfDonation = dateOfDonation;
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", donorId=" + donorId + ", item=" + item + ", amount=" + amount
				+ ", dateOfDonation=" + dateOfDonation + "]";
	}

	

	
	
	
}
