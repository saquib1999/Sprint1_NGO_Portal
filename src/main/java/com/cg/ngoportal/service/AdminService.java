package com.cg.ngoportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;

@Service
public interface AdminService {
	public boolean login(Admin admin);
	public Employee addEmployee(Employee employee) throws DuplicateEmployeeException;
	public Employee modifyEmployee(int employeeId, Employee employee) throws NoSuchEmployeeException;
	public boolean removeEmployee(String username) throws NoSuchEmployeeException;
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException;
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException;
	public List<Employee> findAllEmployee() throws NoSuchEmployeeException;
	public List<DonationDistribution> findAllPendingDonations();
	public DonationDistribution approveDonation(DonationDistribution distribution);
	public boolean logout();
}
