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

import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.service.EmployeeService;
import com.cg.ngoportal.service.EmployeeServiceImpl;
@ControllerAdvice
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/createneedypeople")
	public NeedyPeople create(@RequestBody NeedyPeople needyPeople){
		return employeeService.addNeedyPerson(needyPeople);

	}


	@DeleteMapping("/deleteneedypeople")
	public NeedyPeople add(@RequestBody NeedyPeople needyPeople) {
		return employeeService.removeNeedyPerson(needyPeople);
		
	}
	
	@GetMapping("/needyperson/{id}")
	public ResponseEntity<Optional<NeedyPeople>> getCustById(@PathVariable("id") int id){
		Optional<NeedyPeople> np = employeeService.findNeedyPeopleById(id);
		return new ResponseEntity<>(np, HttpStatus.FOUND);
	}
	
	@GetMapping("/needypeoples")
	public ResponseEntity<List<NeedyPeople>> getAllNeedyPeople(){
		 List<NeedyPeople> npList = employeeService.findAllNeedyPeople();
		return new ResponseEntity<>(npList,HttpStatus.FOUND);
	}
	

}
