package com.cg.ngoportal.exception;

@SuppressWarnings("serial")
public class UserNotLoggedInException extends Exception{
	public UserNotLoggedInException(String message) {
		super(message);
	}
}
