package com.cg.ngoportal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.InvalidNeedyPersonObjectException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.service.EmployeeService;
import com.cg.ngoportal.service.EmployeeServiceImpl;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee/login")
	public ResponseEntity<String> login(@RequestBody Login loginCred) throws NoSuchEmployeeException{
		System.out.println(loginCred);
		
		boolean result = employeeService.login(loginCred.getUsername(), loginCred.getPassword());
		
		return new ResponseEntity<String>("Success",HttpStatus.OK);
			
		
	}
	
	@PostMapping("/create-needy-person")
	public ResponseEntity<NeedyPeople> create(@RequestBody NeedyPeople needyPeople) throws  UserNotLoggedInException, DuplicateNeedyPersonException, InvalidNeedyPersonObjectException{
		return new ResponseEntity<NeedyPeople>(employeeService.addNeedyPerson(needyPeople),HttpStatus.CREATED);

	}


	@DeleteMapping("/delete-needy-person")
	public ResponseEntity<NeedyPeople> add(@RequestBody NeedyPeople needyPeople) throws UserNotLoggedInException, NoSuchNeedyPersonException {
		return new ResponseEntity<NeedyPeople>(employeeService.removeNeedyPerson(needyPeople),HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/needy-person-by-id/{id}")
	public ResponseEntity<NeedyPeople> getCustById(@PathVariable("id") int id) throws UserNotLoggedInException, NoSuchNeedyPersonException{
		NeedyPeople np = employeeService.findNeedyPeopleById(id);
		return new ResponseEntity<>(np, HttpStatus.FOUND);
	}
	
	@GetMapping("/needy-people")
	public ResponseEntity<List<NeedyPeople>> getAllNeedyPeople() throws UserNotLoggedInException{
		 List<NeedyPeople> npList = employeeService.findAllNeedyPeople();
		return new ResponseEntity<>(npList,HttpStatus.FOUND);
	}
	
	@GetMapping("/employee/logout")
	public ResponseEntity<String> logOut(){
		employeeService.logOut();
		return new ResponseEntity<>("Logout Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/needy-person-by-name/{name}")
	public ResponseEntity<List<NeedyPeople>> getAllNeedyPeopleByName(@PathVariable("name" ) String name) throws UserNotLoggedInException{
		List<NeedyPeople> npList = employeeService.findNeedyPeopleByName(name);
		return new ResponseEntity<>(npList,HttpStatus.FOUND);
	}
	
	@PostMapping("/help")
	public ResponseEntity<DonationDistribution> helpNeedyPerson(@RequestBody DonationDistribution donationDistribution) throws UserNotLoggedInException{
		return new ResponseEntity<DonationDistribution>(employeeService.helpNeedyPerson(donationDistribution),HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/employee/approve")
	public ResponseEntity<DonationDistribution> approveRequest(@RequestBody Request request ) throws UserNotLoggedInException{
		return new ResponseEntity<DonationDistribution>(employeeService.approveDonationDistribution(request),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/employee/pending-request")
	public ResponseEntity<List<Request>> pendingRequestEmployeeLevel() throws UserNotLoggedInException{
		return new ResponseEntity<List<Request>>(employeeService.checkPendingRequests(),HttpStatus.FOUND);
	}
	
	@GetMapping("/approved-list")
	public ResponseEntity<List<DonationDistribution>> approvedDonationList() throws UserNotLoggedInException{
		return new ResponseEntity<List<DonationDistribution>>(employeeService.checkApprovedDistribution(),HttpStatus.FOUND);
	}

}
