package com.cg.ngoportal.exception;

@SuppressWarnings("serial")
public class NoSuchEmployeeException extends RuntimeException {
	public NoSuchEmployeeException(String message) {
		super(message);
	}

}
