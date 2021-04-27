package com.cg.ngoportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.model.Employee;
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
		return new ResponseEntity<NeedyPeople>(needyperson, HttpStatus.OK);
	}

	@PostMapping("/request/{id}")
	public ResponseEntity<Request> needyPersonRequest(@RequestBody Request request, @PathVariable int id) {
		needyPeopleService.requestForHelp(request,id);
		return new ResponseEntity<Request>(request, HttpStatus.OK);
	}

	@GetMapping("/request-status/{id}")
	public ResponseEntity<List<Request>> RequestStatus(@PathVariable int id) {
		return new ResponseEntity<List<Request>>((List<Request>) needyPeopleService.requestStatusCheck(id),
				HttpStatus.OK);
	}

	@PatchMapping("/needy-person/modify-details/{id}")
	public ResponseEntity<NeedyPeople> updateNeedyPerson(@RequestBody NeedyPeople needyPerson, @PathVariable int id) {
		return new ResponseEntity<NeedyPeople>(needyPeopleService.modifyNeedyPerson(needyPerson, id), HttpStatus.OK);

	}
	
	@GetMapping("/find-needy-person-by-id/{id}")
	public ResponseEntity<NeedyPeople> findNeedyPersonById(@PathVariable int id) {
		return new ResponseEntity<NeedyPeople>(needyPeopleService.getNeedyPersonById(id), HttpStatus.OK);
	}

	@GetMapping("/needy-person/logout")
	public ResponseEntity<String> logoutNeedyPerson() {
		return new ResponseEntity<String>(needyPeopleService.logOut(), HttpStatus.OK);
	}

}