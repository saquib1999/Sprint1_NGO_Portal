package com.cg.ngoportal.controller;

public class EmailToDonor {
	private String email;

	public EmailToDonor(String email) {
		super();
		this.email = email;
	}

	public EmailToDonor() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "EmailToDonor [email=" + email + "]";
	}
	

}
