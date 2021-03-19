package com.cg.ngoportal.model;

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
	private String username;
	private String password;

	
	
	public Admin(String adminUsername, String adminPassword) {
		super();
		this.username = adminUsername;
		this.password = adminPassword;
	}
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public int getAdminId() {
		return id;
	}
	public void setAdminId(int adminId) {
		this.id = adminId;
	}
	public String getAdminUsername() {
		return username;
	}
	public void setAdminUsername(String adminUsername) {
		this.username = adminUsername;
	}
	public String getAdminPassword() {
		return password;
	}
	public void setAdminPassword(String adminPassword) {
		this.password = adminPassword;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + id + ", adminUsername=" + username + ", adminPassword=" + password
				+ "]";
	}
	
	
	
}
