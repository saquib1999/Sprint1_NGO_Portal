package com.cg.ngoportal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NEmailandUsername")
public class EmailAndUsername {
	@Id
	@GeneratedValue(generator = "NEMAILID", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NEMAILID", sequenceName = "NEMAIL_ID",allocationSize=1,initialValue = 1)
	
	private int id;	
	private String email;
	private String username;
	
	public EmailAndUsername() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
