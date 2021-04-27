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
import com.cg.ngoportal.model.HttpRequestBody;
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


	@DeleteMapping("/delete-needy-person/{id}")
	public ResponseEntity<NeedyPeople> add(@PathVariable int id) {
		return new ResponseEntity<NeedyPeople>(employeeService.removeNeedyPerson(id),HttpStatus.OK);
		
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
		System.out.println(donationDistribution);
		return new ResponseEntity<DonationDistribution>(employeeService.helpNeedyPerson(donationDistribution),HttpStatus.OK);
		
	}
	
	@PostMapping("/employee/approve")
	public ResponseEntity<DonationDistribution> approveRequest(@RequestBody HttpRequestBody requestBody) {
		int employeeId = requestBody.getId();
		Request request = requestBody.getRequest();
		if (request.getStatus() == RequestStatus.REJECTED_BY_EMPLOYEE)
			return new ResponseEntity<>(employeeService.approveDonationDistributionEmployeeLevel(employeeId, request, null), HttpStatus.CONFLICT);
		return new ResponseEntity<DonationDistribution>(employeeService.approveDonationDistributionEmployeeLevel(employeeId, request, new DonationDistribution(new DonationItem())),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/employee/pending-request")
	public ResponseEntity<List<Request>> pendingRequestEmployeeLevel() {
		return new ResponseEntity<List<Request>>(employeeService.checkPendingRequests(),HttpStatus.OK);
	}
	
	@GetMapping("/approved-list/{id}")
	public ResponseEntity<List<DonationDistribution>> approvedDonationList(@PathVariable("id" ) int employeeId) {
		return new ResponseEntity<List<DonationDistribution>>(employeeService.checkApprovedDistribution(employeeId),HttpStatus.OK);
	}
	
	@GetMapping("/distributed-list/{id}")
	public ResponseEntity<List<DonationDistribution>> donatedList(@PathVariable("id" ) int employeeId) {
		return new ResponseEntity<List<DonationDistribution>>(employeeService.checkDistributedList(employeeId),HttpStatus.OK);
	}
	@GetMapping("/pending-list/{id}")
	public ResponseEntity<List<DonationDistribution>> pendingList(@PathVariable("id" ) int employeeId) {
		return new ResponseEntity<List<DonationDistribution>>(employeeService.checkPendingList(employeeId),HttpStatus.OK);
	}

}
