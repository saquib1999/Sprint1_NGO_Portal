package com.cg.ngoportal.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.User;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	Optional<Employee> findByUserLoginDetails(User userId);
	
}
