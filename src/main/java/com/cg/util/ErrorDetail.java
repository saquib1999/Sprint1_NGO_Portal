package com.cg.util;

import java.util.Date;

public class ErrorDetail {
	private Date timestamp;
	private String errorMessage;
	private String errorDetails;

	public ErrorDetail(Date timestamp, String errorMessage, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	@Override
	public String toString() {
		return "ErrorDetail [timestamp=" + timestamp + ", errorMessage=" + errorMessage + ", errorDetails="
				+ errorDetails + "]";
	}

}
