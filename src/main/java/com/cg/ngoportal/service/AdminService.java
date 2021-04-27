package com.cg.ngoportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.IncorrectUsernameOrPasswordException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.DonationBox;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;

@Service
public interface AdminService {
	public Admin addUser(Admin admin);
	public String login(Admin admin) throws IncorrectUsernameOrPasswordException;
	public Employee addEmployee(Employee employee) throws DuplicateEmployeeException, UserNotLoggedInException;
	public Employee modifyEmployee(int employeeId, Employee employee) throws NoSuchEmployeeException, UserNotLoggedInException;
	public Employee removeEmployee(String username) throws NoSuchEmployeeException, UserNotLoggedInException;
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException, UserNotLoggedInException;
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException, UserNotLoggedInException;
	public List<Employee> findAllEmployee() throws NoSuchEmployeeException, UserNotLoggedInException;
	public List<DonationDistribution> findAllPendingDonations() throws UserNotLoggedInException;
	public DonationDistribution approveDonation(DonationDistribution distribution) throws UserNotLoggedInException;
	public String logout();
	public DonationBox addNgo(DonationBox newNgo) throws UserNotLoggedInException;
	public List<DonationBox> findAllNgo() throws UserNotLoggedInException;
	public DonationDistribution findDonationDistribution(int id);
}