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
import com.cg.ngoportal.exception.NoSuchEmployeeException;
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
		public ResponseEntity<Admin> login(@RequestBody Admin admin){
			
			adminService.login(admin);
			return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
		}
		//createEmployee
		//working
		@PostMapping("/add-employee")
		public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws DuplicateEmployeeException{
			adminService.addEmployee(employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
		}
		
		//working
		//modifyEmployee
		@PostMapping("/modify-employee")
		public ResponseEntity<Employee> modifyEmployee(@RequestBody Employee employee) throws NoSuchEmployeeException{
			adminService.modifyEmployee(employee.getId(), employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		
		//deleteEmployee 
		@PostMapping("/delete-employee/{username}")
		public String deleteEmployee(@PathVariable String username) throws NoSuchEmployeeException {
			adminService.removeEmployee(username);
			return "Deleted";
		}
		
		//working
		//find EmployeeByID
		@GetMapping("/find-employee-by-id/{id}")
		public ResponseEntity<Employee> findEmployeeById(@PathVariable int id) throws NoSuchEmployeeException{
			Employee employee = adminService.findEmployeeById(id);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		
		//working
		//find EmployeeByName
		@GetMapping("/find-employee-by-name/{name}")
		public ResponseEntity<List<Employee>> findEmployeeByName(@PathVariable String name) throws NoSuchEmployeeException{
			List<Employee> elist = adminService.findEmployeeByName(name);
			return new ResponseEntity<List<Employee>>(elist, HttpStatus.OK);
		}
		//working
		//find AllEmployees
		@GetMapping("/find-all-employees")
		public ResponseEntity<List<Employee>> findAllEmployee() throws NoSuchEmployeeException{
			List<Employee> elist = adminService.findAllEmployee();
			return new ResponseEntity<List<Employee>>(elist, HttpStatus.OK);
		}
		
		@GetMapping("/find-all-donations")
		public ResponseEntity<List<DonationDistribution>> findAllDonations(){
			List<DonationDistribution> dlist = adminService.findAllPendingDonations();
			return new ResponseEntity<List<DonationDistribution>>(dlist, HttpStatus.OK);
		}
		
		//approveDonations
		@PutMapping("/approve-donation")
		public ResponseEntity<DonationDistribution> approveDonation(@RequestBody DonationDistribution dd){
			DonationDistribution donation = adminService.approveDonation(dd);
			return new ResponseEntity<DonationDistribution>(donation, HttpStatus.ACCEPTED);
		}
		//working
		//logout
		@GetMapping("/logout")
		public ResponseEntity<?> logout(){
			adminService.logout();
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		
}
