package com.cg.ngoportal.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.cg.util.ErrorDetail;

@ControllerAdvice
public class DonorExceptionHandler {
	@ResponseBody
	@ExceptionHandler(NoSuchDonorException.class)
	ResponseEntity<?> exceptionHandler(NoSuchDonorException e,WebRequest request){
		ErrorDetail errorDetail = new ErrorDetail(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
		
	}
	
	@ResponseBody
	@ExceptionHandler(DuplicateDonorException.class)
	ResponseEntity<?> exceptionHandler(DuplicateDonorException e,WebRequest request){
		ErrorDetail errorDetail = new ErrorDetail(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	
	}
	
	@ResponseBody
	@ExceptionHandler(UserNotLoggedInException.class)
	ResponseEntity<?> exceptionHandler(UserNotLoggedInException e,WebRequest request){
		ErrorDetail errorDetail = new ErrorDetail(new Date(), e.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	
	}
	

}
