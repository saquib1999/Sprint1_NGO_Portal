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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.DonationBox;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.service.AdminService;

@RestController
@CrossOrigin
public class AdminController {
	
	@Autowired
	AdminService adminService;
		//login
		//working
		@GetMapping("/admin/login")
		public ResponseEntity<String> login(@RequestBody Admin admin){
			return new ResponseEntity<String>(adminService.login(admin), HttpStatus.OK);
		}
		//createEmployee
		//working
		@PostMapping("/add-employee")
		public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
			adminService.addEmployee(employee);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
		
		//working
		//modifyEmployee
		@PutMapping("/modify-employee")
		public ResponseEntity<Employee> modifyEmployee(@RequestBody Employee employee){
			return new ResponseEntity<Employee>(adminService.modifyEmployee(employee.getId(), employee), HttpStatus.OK);
		}
		
		//working
		//deleteEmployee 
		@GetMapping("/delete-employee/{username}")
		public ResponseEntity<Employee> deleteEmployee(@PathVariable String username) {
			return new ResponseEntity<Employee>(adminService.removeEmployee(username), HttpStatus.OK);
		}
		
		//working
		//find EmployeeByID
		@GetMapping("/find-employee-by-id/{id}")
		public ResponseEntity<Employee> findEmployeeById(@PathVariable int id) {
			return new ResponseEntity<Employee>(adminService.findEmployeeById(id), HttpStatus.OK);
		}
		
		//working
		//find EmployeeByName
		@GetMapping("/find-employee-by-name/{name}")
		public ResponseEntity<List<Employee>> findEmployeeByName(@PathVariable String name){
			return new ResponseEntity<List<Employee>>(adminService.findEmployeeByName(name), HttpStatus.OK);
		}
		
		//working
		//find AllEmployees
		@GetMapping("/find-all-employees")
		public ResponseEntity<List<Employee>> findAllEmployee() {
			return new ResponseEntity<List<Employee>>(adminService.findAllEmployee(), HttpStatus.OK);
		}
		
		@GetMapping("/find-all-donations")
		public ResponseEntity<List<DonationDistribution>> findAllDonations() {
			return new ResponseEntity<List<DonationDistribution>>(adminService.findAllPendingDonations(), HttpStatus.OK);
		}
		
		//approveDonations
		@PatchMapping("/approve-donation")
		public ResponseEntity<DonationDistribution> approveDonation(@RequestBody DonationDistribution dd)  {
			return new ResponseEntity<DonationDistribution>(adminService.approveDonation(dd), HttpStatus.OK);
		}
		
		//working
		@GetMapping("/find-all-ngos")
		public ResponseEntity<List<DonationBox>> findAllNgo()  {
			return new ResponseEntity<List<DonationBox>>(adminService.findAllNgo(), HttpStatus.OK);
		}
		
		
		@PostMapping("/add-ngo")
		public ResponseEntity<DonationBox> addNgo(@RequestBody DonationBox db) {
			return new ResponseEntity<DonationBox>(adminService.addNgo(db), HttpStatus.OK);
		}
		//logout
		@GetMapping("/admin/logout")
		public ResponseEntity<String> logout(){
			return new ResponseEntity<String>(adminService.logout(), HttpStatus.OK);
		}
		@GetMapping("/donation/{id}")
		public ResponseEntity<DonationDistribution> findDonationById(@PathVariable int id){
			return new ResponseEntity<DonationDistribution>(adminService.findDonationDistribution(id), HttpStatus.OK);
		}
		
}