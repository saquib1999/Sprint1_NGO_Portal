package com.cg.ngoportal.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NDonation")
public class Donation {
	@Id
	private int donationId;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donor_id", referencedColumnName = "donorId")

	private Donor donor;
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "itemId")

	private DonationItem item;
	private double donationAmount;
	private Date donationDate;
	
	
	
	public Donation(int donationId, Donor donor, DonationItem item, double donationAmount, Date donationDate) {
		super();
		this.donationId = donationId;
		this.donor = donor;
		this.item = item;
		this.donationAmount = donationAmount;
		this.donationDate = donationDate;
	}
	
	public Donation() {
		// TODO Auto-generated constructor stub
	}
	public int getDonationId() {
		return donationId;
	}
	public void setDonationId(int donationId) {
		this.donationId = donationId;
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
		return donationAmount;
	}
	public void setDonationAmount(double donationAmount) {
		this.donationAmount = donationAmount;
	}
	public Date getDonationDate() {
		return donationDate;
	}
	public void setDonationDate(Date donationDate) {
		this.donationDate = donationDate;
	}
	@Override
	public String toString() {
		return "Donation [donationId=" + donationId + ", donor=" + donor + ", item=" + item + ", donationAmount="
				+ donationAmount + ", donationDate=" + donationDate + "]";
	}
	
	
}
