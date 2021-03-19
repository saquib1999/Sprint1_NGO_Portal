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
	
	
	
	
	public Donation(Donor donor, DonationItem item, double amount, Date date) {
		super();
		this.donor = donor;
		this.item = item;
		this.amount = amount;
		this.date = date;
	}

	public Donation() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Donation [id=" + id + ", donor=" + donor + ", item=" + item + ", amount=" + amount + ", date=" + date
				+ "]";
	}

	
	
	
}
