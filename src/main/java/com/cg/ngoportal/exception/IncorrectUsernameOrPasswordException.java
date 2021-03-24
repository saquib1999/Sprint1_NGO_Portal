package com.cg.ngoportal.exception;

@SuppressWarnings("serial")
public class IncorrectUsernameOrPasswordException extends Exception{
	public IncorrectUsernameOrPasswordException(String message) {
		super(message);
	}
}
