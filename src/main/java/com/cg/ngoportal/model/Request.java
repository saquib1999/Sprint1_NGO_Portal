package com.cg.ngoportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NRequest")
public class Request {

	@Id
	@GeneratedValue(generator = "NREQUEST_ID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NREQUEST_ID", sequenceName = "NREQUESTID",allocationSize=1,initialValue = 100)
	private int id;
	
	
	private int needyPersonId;
	
	private DonationType donationType;
	private double amountOrQuantity;
	private String reason;
	private RequestStatus status;
	

	public Request(int needyPersonId, DonationType donationType, double amount, String reason) {
		super();
		this.needyPersonId = needyPersonId;
		this.donationType = donationType;
		this.amountOrQuantity = amount;
		this.reason = reason;
		this.status = RequestStatus.PENDING;
	}

//	public Request(NeedyPeople needyPerson, DonationType donationType, String quantity, String reason) {
//		super();
//		this.needyPerson = needyPerson;
//		this.donationType = donationType;
//		this.quantity = quantity;
//		this.reason = reason;
//		this.status = RequestStatus.PENDING;
//	}

	public Request() {
		super();
	}

	public int getRequestId() {
		return id;
	}

	public void setRequestId(int requestId) {
		this.id = requestId;
	}

	public DonationType getDonationType() {
		return donationType;
	}

	public void setDonationType(DonationType donationType) {
		this.donationType = donationType;
	}

	public double getAmountOrQuantity() {
		return amountOrQuantity;
	}

	public void setAmountOrQuantity(double amount) {
		this.amountOrQuantity = amount;
	}

//	public String getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(String quantity) {
//		this.quantity = quantity;
//	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}


	public int getNeedyPersonId() {
		return needyPersonId;
	}

	public void setNeedyPersonId(int needyPersonId) {
		this.needyPersonId = needyPersonId;
	}

	public RequestStatus getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", needyPersonId=" + needyPersonId + ", donationType=" + donationType
				+ ", amountOrQuantity=" + amountOrQuantity + ", reason=" + reason + ", status=" + status + "]";
	}

	
}