package com.cg.ngoportal.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String cardNumber;
	private String nameOnCard;
	private int cvvNumber;
	private Date dateOfDonation;
	private String description;
	private String ngo;
	private String expiryDate;
	
	


	public String getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public String getNameOnCard() {
		return nameOnCard;
	}


	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}


	public int getCvvNumber() {
		return cvvNumber;
	}


	public void setCvvNumber(int cvvNumber) {
		this.cvvNumber = cvvNumber;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	public Donation(int donorId, DonationItem item, double amount, String cardNumber, String nameOnCard, int cvvNumber,
			Date dateOfDonation, String description, String ngo, String expiryDate) {
		super();
		this.donorId = donorId;
		this.item = item;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.cvvNumber = cvvNumber;
		this.dateOfDonation = dateOfDonation;
		this.description = description;
		this.ngo = ngo;
		this.expiryDate = expiryDate;
	}


	public Donation(int donorId, double amount, String cardNumber, String nameOnCard, int cvvNumber,
			Date dateOfDonation, String description, String ngo, String expiryDate) {
		super();
		this.donorId = donorId;
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.cvvNumber = cvvNumber;
		this.dateOfDonation = dateOfDonation;
		this.description = description;
		this.ngo = ngo;
		this.expiryDate = expiryDate;
	}


	public Donation(double amount, String cardNumber, String nameOnCard, int cvvNumber, Date dateOfDonation,
			String description, String ngo) {
		super();
		this.amount = amount;
		this.cardNumber = cardNumber;
		this.nameOnCard = nameOnCard;
		this.cvvNumber = cvvNumber;
		this.dateOfDonation = dateOfDonation;
		this.description = description;
		this.ngo = ngo;
	}
	
	
//	public Donation(DonationItem item, double amount) {
//		super();
//		this.item = item;
//		this.amount = amount;
//	}
//	
	public Donation(int donorId, DonationItem item, double amount, Date dateOfDonation) {
		super();
		this.donorId = donorId;
//		this.item = item;
		this.amount = amount;
		this.dateOfDonation = dateOfDonation;
	}
	
	

	

	public Donation(int donorId, DonationItem item, double amount, Date dateOfDonation, String ngo) {
		super();
		this.donorId = donorId;
//		this.item = item;
		this.amount = amount;
		this.dateOfDonation = dateOfDonation;
		this.ngo = ngo;
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
	
	

	public String getNgo() {
		return ngo;
	}

	public void setNgo(String ngo) {
		this.ngo = ngo;
	}


	@Override
	public String toString() {
		return "Donation [donorId=" + donorId + ", amount=" + amount + ", cardNumber=" + cardNumber + ", nameOnCard="
				+ nameOnCard + ", dateOfDonation=" + dateOfDonation + ", description=" + description + ", ngo=" + ngo
				+ ", expiryDate=" + expiryDate + "]";
	}


	

	

	

	

	
	
	
}