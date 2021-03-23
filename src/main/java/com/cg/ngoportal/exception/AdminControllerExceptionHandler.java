package com.cg.ngoportal.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.cg.ngoportal.utilities.ErrorDetail;


@ControllerAdvice
public class AdminControllerExceptionHandler {

	@ResponseBody
	@ExceptionHandler(DuplicateEmployeeException.class)
	ResponseEntity<?> exceptionHandler(DuplicateEmployeeException de, WebRequest request){
		ErrorDetail errorDetail = new ErrorDetail(new Date(), de.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(NoSuchEmployeeException.class)
	ResponseEntity<?> exceptionHandler(NoSuchEmployeeException de, WebRequest request){
		ErrorDetail errorDetail = new ErrorDetail(new Date(), de.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}
}
