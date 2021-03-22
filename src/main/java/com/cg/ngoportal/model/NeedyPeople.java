package com.cg.ngoportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

@Entity
@Table(name = "NNeedyPeople")
public class NeedyPeople {
	@Id
    @GeneratedValue(generator = "NNP", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NNP", sequenceName = "NN_P",allocationSize=1,initialValue = 1000)
	private int id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private double familyIncome;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	
	private User userLoginDetails;
	
	private Address address;
	
	
	public NeedyPeople() {
		// TODO Auto-generated constructor stub
	}
	
	



	public NeedyPeople(String name, String phone, double familyIncome, User userLoginDetails,
			Address address) {
		super();
		this.name = name;
		this.phone = phone;
		this.familyIncome = familyIncome;
		this.userLoginDetails = userLoginDetails;
		this.address = address;
	}





	

	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public int getId() {
		return id;
	}



	public User getUserLoginDetails() {
		return userLoginDetails;
	}





	public void setUserLoginDetails(User userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
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
		return "NeedyPeople [id=" + id + ", name=" + name + ", phone=" + phone + ", familyIncome=" + familyIncome
				+ ", userLoginDetails=" + userLoginDetails + ", address=" + address + "]";
	}

	
	
	
}
