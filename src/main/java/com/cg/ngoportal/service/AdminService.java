package com.cg.ngoportal.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;

@Service
public interface AdminService {
	public boolean login(String username,String password)throws SQLException;
	public boolean addEmployee(Employee employee) throws DuplicateEmployeeException;
	public Employee modifyEmployee(Employee employee) throws NoSuchEmployeeException;
	public boolean removeEmployee(int employeeId) throws NoSuchEmployeeException;
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException;
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException;
	public List<Employee> findAllEmployee() throws NoSuchEmployeeException;
	public boolean approveDonation(DonationDistribution distribution);
}
