package com.cg.ngoportal.dao;

import java.sql.SQLException;
import java.util.List;

import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;

public interface AdminDao {
	public int login(String username, String password) throws SQLException;
	public int createEmployee(Employee employee)throws SQLException;
	public Employee updateEmployee(Employee employee)throws SQLException;
	public int deleteEmployee(int employeeId)throws SQLException;
	public Employee readEmployeeById(int employeeId)throws SQLException;
	public List<Employee> readEmployeeByName(String name)throws SQLException;
	public List<Employee> readAllEmployees()throws SQLException;
	public boolean approveDonation(DonationDistribution distribution);
}
