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
	
	private int adminId;
	private String adminUsername;
	private String adminPassword;

	
	
	public Admin(String adminUsername, String adminPassword) {
		super();
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminUsername=" + adminUsername + ", adminPassword=" + adminPassword
				+ "]";
	}
	
	
	
}
