package com.cg.ngoportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.IncorrectUsernameOrPasswordException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
		//login
		//working
		@GetMapping("/login")
		public ResponseEntity<String> login(@RequestBody Admin admin) throws IncorrectUsernameOrPasswordException{
			return new ResponseEntity<String>(adminService.login(admin), HttpStatus.ACCEPTED);
		}
		//createEmployee
		//working
		@PostMapping("/add-employee")
		public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws DuplicateEmployeeException, UserNotLoggedInException{
			adminService.addEmployee(employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		}
		
		//working
		//modifyEmployee
		@PutMapping("/modify-employee")
		public ResponseEntity<Employee> modifyEmployee(@RequestBody Employee employee) throws NoSuchEmployeeException, UserNotLoggedInException{
			return new ResponseEntity<Employee>(adminService.modifyEmployee(employee.getId(), employee), HttpStatus.OK);
		}
		
		//deleteEmployee 
		@DeleteMapping("/delete-employee/{username}")
		public String deleteEmployee(@PathVariable String username) throws NoSuchEmployeeException, UserNotLoggedInException {
			adminService.removeEmployee(username);
			return "Deleted";
		}
		
		//working
		//find EmployeeByID
		@GetMapping("/find-employee-by-id/{id}")
		public ResponseEntity<Employee> findEmployeeById(@PathVariable int id) throws NoSuchEmployeeException, UserNotLoggedInException{
			return new ResponseEntity<Employee>(adminService.findEmployeeById(id), HttpStatus.OK);
		}
		
		//working
		//find EmployeeByName
		@GetMapping("/find-employee-by-name/{name}")
		public ResponseEntity<List<Employee>> findEmployeeByName(@PathVariable String name) throws NoSuchEmployeeException, UserNotLoggedInException{
			return new ResponseEntity<List<Employee>>(adminService.findEmployeeByName(name), HttpStatus.OK);
		}
		//working
		//find AllEmployees
		@GetMapping("/find-all-employees")
		public ResponseEntity<List<Employee>> findAllEmployee() throws NoSuchEmployeeException, UserNotLoggedInException{
			return new ResponseEntity<List<Employee>>(adminService.findAllEmployee(), HttpStatus.OK);
		}
		
		@GetMapping("/find-all-donations")
		public ResponseEntity<List<DonationDistribution>> findAllDonations() throws UserNotLoggedInException{
			return new ResponseEntity<List<DonationDistribution>>(adminService.findAllPendingDonations(), HttpStatus.OK);
		}
		
		//approveDonations
		@PutMapping("/approve-donation")
		public ResponseEntity<DonationDistribution> approveDonation(@RequestBody DonationDistribution dd) throws UserNotLoggedInException{
			return new ResponseEntity<DonationDistribution>(adminService.approveDonation(dd), HttpStatus.ACCEPTED);
		}
		//working
		//logout
		@GetMapping("/logout")
		public ResponseEntity<String> logout(){
			return new ResponseEntity<String>(adminService.logout(), HttpStatus.ACCEPTED);
		}
		
}
