package com.cg.ngoportal.exception;

@SuppressWarnings("serial")
public class IncorrectUsernameOrPasswordException extends RuntimeException{
	public IncorrectUsernameOrPasswordException(String message) {
		super(message);
	}
}
