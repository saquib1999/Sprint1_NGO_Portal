package com.cg.ngoportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.service.NeedyPeopleService;

@RestController
@RequestMapping("/needypeople")
public class NeedyPeopleController {

	@Autowired
	private NeedyPeopleService needyPeopleService;
	
	@PostMapping("/register")
	public ResponseEntity<NeedyPeople> needyPersonRegister(@RequestBody NeedyPeople needyperson) throws DuplicateNeedyPersonException {
		needyPeopleService.register(needyperson);
		return new ResponseEntity<NeedyPeople>(needyperson, HttpStatus.CREATED);
	}

	@GetMapping("/login") 
	public ResponseEntity<String> loginNeedyPerson(@RequestBody Login login) throws NoSuchNeedyPersonException {
		return new ResponseEntity<String>(needyPeopleService.loginNeedyPerson(login.getUsername(), login.getPassword()),HttpStatus.ACCEPTED);
	}

	@PostMapping("/request")
	public ResponseEntity<Request> needyPersonRequest(@RequestBody Request request) throws UserNotLoggedInException {
		needyPeopleService.requestForHelp(request);
		return new ResponseEntity<Request>(request, HttpStatus.ACCEPTED);
	}	

	@GetMapping("/request-status")
	public ResponseEntity<List<Request>> RequestStatus() throws UserNotLoggedInException {
		return new ResponseEntity<List<Request>>((List<Request>) needyPeopleService.requestStatusCheck(),HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<String> logoutNeedyPerson() {
		return new ResponseEntity<String>(needyPeopleService.logOut(),HttpStatus.OK);

	}

}
