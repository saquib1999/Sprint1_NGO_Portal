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
@Table(name = "NDonationDistribution")
public class DonationDistribution {
	@Id
    @GeneratedValue(generator = "NDIS_ID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NDIS_ID", sequenceName = "NDISID",allocationSize=1, initialValue = 1000)	

	private int id;
	private int requestId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private NeedyPeople person;
	
	@OneToOne(cascade = CascadeType.ALL)
	private DonationItem item;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Employee distributedBy;
	
	private double amountDistributed;
	
	private Date dateOfDistribution;
	private Date approvalOrRejectedDate;
	private DonationDistributionStatus status;
	
	
	
	public DonationDistribution() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DonationDistribution( NeedyPeople person, DonationItem item, Employee distributedBy,
			double amountDistributed, Date dateOfDistribution, Date approvalOrRejectedDate,
			DonationDistributionStatus status) {
		super();
		this.person = person;
		this.item = item;
		this.distributedBy = distributedBy;
		this.amountDistributed = amountDistributed;
		this.dateOfDistribution = dateOfDistribution;
		this.approvalOrRejectedDate = approvalOrRejectedDate;
		this.status = status;
	}
	public DonationDistribution(DonationItem item) {
		// TODO Auto-generated constructor stub
		this.item = item;
	}


	public int getId() {
		return id;
	}
	
	public NeedyPeople getPerson() {
		return person;
	}
	public void setPerson(NeedyPeople person) {
		this.person = person;
	}
	public DonationItem getItem() {
		return item;
	}
	public void setItem(DonationItem item) {
		this.item = item;
	}
	public Employee getDistributedBy() {
		return distributedBy;
	}
	public void setDistributedBy(Employee distributedBy) {
		this.distributedBy = distributedBy;
	}
	public double getAmountDistributed() {
		return amountDistributed;
	}
	public void setAmountDistributed(double amountDistributed) {
		this.amountDistributed = amountDistributed;
	}
	public Date getDateOfDistribution() {
		return dateOfDistribution;
	}
	public void setDateOfDistribution(Date dateOfDistribution) {
		this.dateOfDistribution = dateOfDistribution;
	}
	public Date getApprovalOrRejectedDate() {
		return approvalOrRejectedDate;
	}
	public void setApprovalOrRejectedDate(Date approvalOrRejectedDate) {
		this.approvalOrRejectedDate = approvalOrRejectedDate;
	}
	public DonationDistributionStatus getStatus() {
		return status;
	}
	public void setStatus(DonationDistributionStatus status) {
		this.status = status;
	}
	
	
	public int getRequestId() {
		return requestId;
	}


	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}


	@Override
	public String toString() {
		return "DonationDistribution [id=" + id + ", requestId=" + requestId + ", person=" + person + ", item=" + item
				+ ", distributedBy=" + distributedBy + ", amountDistributed=" + amountDistributed
				+ ", dateOfDistribution=" + dateOfDistribution + ", approvalOrRejectedDate=" + approvalOrRejectedDate
				+ ", status=" + status + "]";
	}


	
	
	
	
	
}
