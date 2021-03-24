package com.cg.ngoportal.exception;

import java.sql.SQLException;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.cg.ngoportal.utilities.ErrorDetail;
 

@ControllerAdvice
public class ControllerExceptionHandler {

	@ResponseBody
	@ExceptionHandler(IncorrectUsernameOrPasswordException.class)
	ResponseEntity<?> exceptionHandler(IncorrectUsernameOrPasswordException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
	}
	

	@ResponseBody
	@ExceptionHandler(DuplicateEmployeeException.class)
	ResponseEntity<?> exceptionHandler(DuplicateEmployeeException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.CONFLICT);
	}
	

	@ResponseBody
	@ExceptionHandler(NoSuchEmployeeException.class)
	ResponseEntity<?> exceptionHandler(NoSuchEmployeeException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(NoSuchNeedyPersonException.class)
	ResponseEntity<?> exceptionHandler(NoSuchNeedyPersonException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(DuplicateNeedyPersonException.class)
	ResponseEntity<?> exceptionHandler(DuplicateNeedyPersonException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.CONFLICT);
	}
	
	@ResponseBody
	@ExceptionHandler(InvalidNeedyPersonObjectException.class)
	ResponseEntity<?> exceptionHandler(InvalidNeedyPersonObjectException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(UserNotLoggedInException.class)
	ResponseEntity<?> exceptionHandler(UserNotLoggedInException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.UNAUTHORIZED);
	}
	
	@ResponseBody
	@ExceptionHandler(DataIntegrityViolationException.class)
	ResponseEntity<?> exceptionHandler(DataIntegrityViolationException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.EXPECTATION_FAILED);
	}
	
	@ResponseBody
	@ExceptionHandler(NoSuchElementException.class)
	ResponseEntity<?> exceptionHandler(NoSuchElementException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(DuplicateDonorException.class)
	ResponseEntity<?> exceptionHandler(DuplicateDonorException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.CONFLICT);
	}
	
	
	@ResponseBody
	@ExceptionHandler(NoSuchDonorException.class)
	ResponseEntity<?> exceptionHandler(NoSuchDonorException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(SQLException.class)
	ResponseEntity<?> exceptionHandler(SQLException ex, WebRequest request)
	{
		ErrorDetail  errorDetail = new ErrorDetail(new Date(),ex.getMessage(),request.getDescription(false));
	

		return new ResponseEntity<>(errorDetail,HttpStatus.BAD_REQUEST);
	}
	
}
