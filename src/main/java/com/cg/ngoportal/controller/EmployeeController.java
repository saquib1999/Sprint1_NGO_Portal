package com.cg.ngoportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	//@Autowired
	//private EmployeeServiceImpl employeeServiceImpl;
	/*
	@PostMapping("/login")
	public ResponseEntity<boolean> login(@RequestBody String username, @RequestBody String password) throws NoSuchEmployeeException {
		
		return new ResponseEntity<boolean>( employeeServiceImpl.login(username, password),HttpStatus.OK);
	}
	*/
	//@PostMapping("/createNeedyPeople")
	
	

}
