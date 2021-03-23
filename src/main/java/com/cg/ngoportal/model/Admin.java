package com.cg.ngoportal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NAdmin")
public class Admin {
	@Id
	@GeneratedValue(generator = "NADMINID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "NADMINID", sequenceName = "NADMIN_ID",allocationSize=1,initialValue = 1000)
	private int id;
	@Column(unique = true)
	private String username;
	private String password;

	
	
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Admin() {
		// TODO Auto-generated constructor stub
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
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
