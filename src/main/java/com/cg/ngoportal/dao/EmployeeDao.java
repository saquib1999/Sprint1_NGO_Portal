package com.cg.ngoportal.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.User;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{
	Optional<Employee> findByUserLoginDetails(User userId);
	@Query("SELECT e FROM Employee e WHERE e.name = ?1 and e.active = 1")
	public List<Employee> findByName(String name);
	
	@Query("SELECT e FROM Employee e WHERE e.userLoginDetails.username = ?1")
	public Optional<Employee> findByUsername(String username);

	@Query("SELECT e FROM Employee e WHERE e.active = 1")
	public List<Employee> findAllActiveEmployee();

	@Query("SELECT e FROM Employee e WHERE e.id = ?1 and e.active = 1")
	public Optional<Employee> findByEmployeeId(int employeeId);
}
