package com.cg.ngoportal.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer>{
	
}
