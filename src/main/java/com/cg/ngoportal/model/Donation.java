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
	@GeneratedValue(generator = "NDONATIONID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NDONATIONID", sequenceName = "NDONATION_ID",allocationSize=1,initialValue = 1)
	
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	private Donor donor;
	@OneToOne(cascade = CascadeType.ALL)
	private DonationItem item;
	private double amount;
	private Date date;
	
	
	
	public Donation(Donor donor, DonationItem item, double donationAmount, Date donationDate) {
		super();
		this.donor = donor;
		this.item = item;
		this.amount = donationAmount;
		this.date = donationDate;
	}
	
	public Donation() {
		// TODO Auto-generated constructor stub
	}
	public int getDonationId() {
		return id;
	}
	public Donor getDonor() {
		return donor;
	}
	public void setDonor(Donor donor) {
		this.donor = donor;
	}
	public DonationItem getItem() {
		return item;
	}
	public void setItem(DonationItem item) {
		this.item = item;
	}
	public double getDonationAmount() {
		return amount;
	}
	public void setDonationAmount(double donationAmount) {
		this.amount = donationAmount;
	}
	public Date getDonationDate() {
		return date;
	}
	public void setDonationDate(Date donationDate) {
		this.date = donationDate;
	}
	@Override
	public String toString() {
		return "Donation [donationId=" + id + ", donor=" + donor + ", item=" + item + ", donationAmount="
				+ amount + ", donationDate=" + date + "]";
	}
	
	
}
