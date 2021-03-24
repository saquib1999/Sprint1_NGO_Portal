package com.cg.ngoportal.controller;

import com.cg.ngoportal.model.UserType;

public class LoginCred {
	private String username;
	private String  password;
	private UserType userType;
	
	public LoginCred() {
		// TODO Auto-generated constructor stub
	}
	
	
	public LoginCred(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	public LoginCred(String username, String password, UserType userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
	}


	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}

	

	public UserType getUserType() {
		return userType;
	}


	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + ", userType=" + userType + "]";
	}



	
	
	

}
