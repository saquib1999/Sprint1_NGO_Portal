package com.cg.ngoportal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "NAdmin")
public class Admin {
	@Id
	@GeneratedValue(generator = "NADMINID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NADMINID", sequenceName = "NADMIN_ID",allocationSize=1,initialValue = 1000)
	
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	private User userLoginDetails;
	
	public int getId() {
		return id;
	}
	
	
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	

	public Admin( User userLoginDetails) {
		super();
		this.userLoginDetails = userLoginDetails;
	}

	



	public User getUserLoginDetails() {
		return userLoginDetails;
	}



	public void setUserLoginDetails(User userLoginDetails) {
		this.userLoginDetails = userLoginDetails;
	}



	@Override
	public String toString() {
		return "Admin [id=" + id + ", userLoginDetails=" + userLoginDetails + "]";
	}

	


	




	
	
	
	
	
}
