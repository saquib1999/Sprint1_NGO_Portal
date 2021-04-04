package com.cg.ngoportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ngoportal.model.User;
import com.cg.ngoportal.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User loginCred){
		
		return new ResponseEntity<String>(loginService.login(loginCred),HttpStatus.ACCEPTED);
		
	}

}
