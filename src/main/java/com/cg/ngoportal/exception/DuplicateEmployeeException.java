package com.cg.ngoportal.exception;

public class DuplicateEmployeeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DuplicateEmployeeException(String message) {
		super(message);
	}
}
