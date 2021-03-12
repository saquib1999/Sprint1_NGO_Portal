package com.cg.ngoportal.model;

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
@Table(name = "NNeedyPeople")
public class NeedyPeople {
	@Id
    @GeneratedValue(generator = "NNP", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NNP", sequenceName = "NN_P",allocationSize=1,initialValue = 1000)
	private int needyPersonId;
	private String needyPersonName;
	private String phone;
	private double familyIncome;
	
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn( referencedColumnName = "userId")
	private User userDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")

	private Address address;
	
	
	public NeedyPeople() {
		// TODO Auto-generated constructor stub
	}
	
	

	public NeedyPeople(String needyPersonName, String phone, double familyIncome, User user, Address address) {
		super();
		this.needyPersonName = needyPersonName;
		this.phone = phone;
		this.familyIncome = familyIncome;
		this.userDetails = user;
		this.address = address;
	}



	public User getUser() {
		return userDetails;
	}

	public void setUser(User user) {
		this.userDetails = user;
	}

	public int getNeedyPersonId() {
		return needyPersonId;
	}
	public void setNeedyPersonId(int needyPersonId) {
		this.needyPersonId = needyPersonId;
	}
	public String getNeedyPersonName() {
		return needyPersonName;
	}
	public void setNeedyPersonName(String needyPersonName) {
		this.needyPersonName = needyPersonName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(double familyIncome) {
		this.familyIncome = familyIncome;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "NeedyPeople [needyPersonId=" + needyPersonId + ", needyPersonName=" + needyPersonName + ", phone="
				+ phone + ", familyIncome=" + familyIncome + ", user=" + userDetails + ", address=" + address + "]";
	}
	
	
	
	
}
