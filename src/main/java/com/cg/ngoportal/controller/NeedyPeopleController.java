package com.cg.ngoportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.service.NeedyPeopleService;

@RestController
@CrossOrigin
public class NeedyPeopleController {

	@Autowired
	private NeedyPeopleService needyPeopleService;
	
	
	@PostMapping("/needy-person/register")
	public ResponseEntity<NeedyPeople> needyPersonRegister(@RequestBody NeedyPeople needyperson)
			throws DuplicateNeedyPersonException {
		needyPeopleService.register(needyperson);
		return new ResponseEntity<NeedyPeople>(needyperson, HttpStatus.CREATED);
	}

	
		
	

	@PostMapping("/request")
	public ResponseEntity<Request> needyPersonRequest(@RequestBody Request request) {
		needyPeopleService.requestForHelp(request);
		return new ResponseEntity<Request>(request, HttpStatus.ACCEPTED);
	}

	@GetMapping("/request-status")
	public ResponseEntity<List<Request>> RequestStatus() {
		return new ResponseEntity<List<Request>>((List<Request>) needyPeopleService.requestStatusCheck(),
				HttpStatus.OK);
	}
	
	@PatchMapping("/needy-person/modify-details")
	public ResponseEntity<NeedyPeople> updateNeedyPerson(@RequestBody NeedyPeople needyPerson) {
		return new ResponseEntity<NeedyPeople>(needyPeopleService.modifyNeedyPerson(needyPerson), HttpStatus.ACCEPTED);
		
	}

	@GetMapping("/needy-person/logout")
	public ResponseEntity<String> logoutNeedyPerson() {
		return new ResponseEntity<String>(needyPeopleService.logOut(), HttpStatus.OK);

	}

}