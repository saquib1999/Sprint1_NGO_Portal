package com.cg.ngoportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationItem;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.RequestStatus;
import com.cg.ngoportal.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@PostMapping("/create-needy-person")
	public ResponseEntity<NeedyPeople> create(@RequestBody NeedyPeople needyPeople) {
		return new ResponseEntity<NeedyPeople>(employeeService.addNeedyPerson(needyPeople),HttpStatus.OK);

	}


	@DeleteMapping("/delete-needy-person")
	public ResponseEntity<NeedyPeople> add(@RequestBody NeedyPeople needyPeople) {
		return new ResponseEntity<NeedyPeople>(employeeService.removeNeedyPerson(needyPeople),HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/needy-person-by-id/{id}")
	public ResponseEntity<NeedyPeople> getCustById(@PathVariable("id") int id) {
		NeedyPeople np = employeeService.findNeedyPeopleById(id);
		return new ResponseEntity<>(np, HttpStatus.OK);
	}
	
	@GetMapping("/needy-people")
	public ResponseEntity<List<NeedyPeople>> getAllNeedyPeople() {
		 List<NeedyPeople> npList = employeeService.findAllNeedyPeople();
		return new ResponseEntity<>(npList,HttpStatus.OK);
	}
	
	@GetMapping("/employee/logout")
	public ResponseEntity<String> logOut(){
		employeeService.logOut();
		return new ResponseEntity<>("Logout Successfully", HttpStatus.OK);
	}
	
	@GetMapping("/needy-person-by-name/{name}")
	public ResponseEntity<List<NeedyPeople>> getAllNeedyPeopleByName(@PathVariable("name" ) String name) {
		List<NeedyPeople> npList = employeeService.findNeedyPeopleByName(name);
		return new ResponseEntity<>(npList,HttpStatus.OK);
	}
	
	@PatchMapping("/help")
	public ResponseEntity<DonationDistribution> helpNeedyPerson(@RequestBody DonationDistribution donationDistribution) {
		return new ResponseEntity<DonationDistribution>(employeeService.helpNeedyPerson(donationDistribution),HttpStatus.OK);
		
	}
	
	@PostMapping("/employee/approve")
	public ResponseEntity<DonationDistribution> approveRequest(@RequestBody Request request ) {
		if (request.getStatus() == RequestStatus.REJECTED_BY_EMPLOYEE)
			return new ResponseEntity<>(employeeService.approveDonationDistributionEmployeeLevel(request, null), HttpStatus.CONFLICT);
		return new ResponseEntity<DonationDistribution>(employeeService.approveDonationDistributionEmployeeLevel(request, new DonationDistribution(new DonationItem())),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/employee/pending-request")
	public ResponseEntity<List<Request>> pendingRequestEmployeeLevel() {
		return new ResponseEntity<List<Request>>(employeeService.checkPendingRequests(),HttpStatus.OK);
	}
	
	@GetMapping("/approved-list")
	public ResponseEntity<List<DonationDistribution>> approvedDonationList() {
		return new ResponseEntity<List<DonationDistribution>>(employeeService.checkApprovedDistribution(),HttpStatus.OK);
	}
	
	@GetMapping("/distributed-list")
	public ResponseEntity<List<DonationDistribution>> donatedList() {
		return new ResponseEntity<List<DonationDistribution>>(employeeService.checkDistributedList(),HttpStatus.OK);
	}

}
