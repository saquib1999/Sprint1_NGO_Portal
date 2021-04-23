package com.cg.ngoportal.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private int id;
	private int userType;

	public JwtResponse(int id, int userType, String jwttoken) {
		this.jwttoken = jwttoken;
		this.id = id;
		this.userType = userType;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
}