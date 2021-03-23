 package com.cg.ngoportal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	
	@Query("SELECT e FROM Employee e WHERE e.name = ?1 and e.Active = 1")
	public List<Employee> findByName(String name);
	
	@Query("SELECT e FROM Employee e WHERE e.userLoginDetails.username = ?1")
	public Optional<Employee> findByUsername(String username);

	@Query("SELECT e FROM Employee e WHERE e.Active = 1")
	public List<Employee> findAllActiveEmployee();

	@Query("SELECT e FROM Employee e WHERE e.id = ?1 and e.Active = 1")
	public Optional<Employee> findByEmployeeId(int employeeId);
	
	//@Query("SELECT e FROM Employee e WHERE e.name =  ?1 AND e.email = ?2 AND and e.phone = ?3")
	//public int findByNameEmailPhone(String name, String email, String phone);
}
