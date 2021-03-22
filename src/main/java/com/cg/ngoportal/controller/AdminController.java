package com.cg.ngoportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.service.AdminService;
import com.cg.ngoportal.service.AdminServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	/*
	//login
	//createEmployee
	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		adminService.addEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
	}
	//modifyEmployee
	@PostMapping("/modifyEmployee")
	public ResponseEntity<Employee> modifyEmployee(@RequestBody Employee employee){
		adminService.modifyEmployee(employee);
		return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);
	}
	//deleteEmployee 
	@DeleteMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable int id) {
		adminService.removeEmployee(id);
		return "Deleted";
	}
	//find EmployeeByID
	@GetMapping("/employeeById/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable int id){
		Employee employee = adminService.findEmployeeById(id);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	//find EmployeeByName
	@GetMapping("/employeeById/{name}")
	public ResponseEntity<List<Employee>> findEmployeeByName(@PathVariable String name){
		List<Employee> elist = adminService.findEmployeeByName(name);
		return new ResponseEntity<Employee>(elist, HttpStatus.OK);
	}
	//find AllEmployees
	public ResponseEntity<List<Employee>> findAllEmployee(){
		
	}
	//approveDonations
	
	*/
}
