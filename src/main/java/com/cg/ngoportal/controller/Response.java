package com.cg.ngoportal.controller;

public class Response {
	private int id;
	private int userType;
	private String token;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Response(int id, int userType, String token) {
		
		super();
		this.id = id;
		this.userType = userType;
		this.token = token;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
