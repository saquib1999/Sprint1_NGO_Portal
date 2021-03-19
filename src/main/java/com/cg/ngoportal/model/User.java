package com.cg.ngoportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NUsers")
public class User {
	@Id
    @GeneratedValue(generator = "NUSER_ID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NUSER_ID", sequenceName = "NUSERID",allocationSize=1,initialValue = 1000)
	private int id;
	
	@Column(unique=true)
	private String username;
	private String password;
	private UserType userType;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", userType=" + userType
				+ "]";
	}
	
	

}
