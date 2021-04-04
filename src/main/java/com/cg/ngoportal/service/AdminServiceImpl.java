package com.cg.ngoportal.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.dao.AdminDao;
import com.cg.ngoportal.dao.DonationBoxDao;
import com.cg.ngoportal.dao.DonationDistributionDao;
import com.cg.ngoportal.dao.EmployeeDao;
import com.cg.ngoportal.dao.RequestDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateEmployeeException;

import com.cg.ngoportal.exception.IncorrectUsernameOrPasswordException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.DonationBox;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.RequestStatus;
import com.cg.ngoportal.model.UserType;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDao adminDaoRepo;
	
	@Autowired
	EmployeeDao employeeDaoRepo;
	
	@Autowired
	DonationDistributionDao donationdistributionDaoRepo;
	
	@Autowired
	UserDao userDaoRepo;
	private boolean loggedIn = false;
	
	@Autowired
	DonationBoxDao donationBoxRepo;
	
	@Autowired
	RequestDao requestRepo;
	
	@Override
	public String login(Admin admin) throws IncorrectUsernameOrPasswordException{
		
//		adminDaoRepo.save(admin);	
			Admin ad = adminDaoRepo.findByUsernameAndPassword(admin.getUsername(), admin.getPassword()).orElseThrow(() -> new IncorrectUsernameOrPasswordException("Invalid Login Credentials"));
			loggedIn = true;
			return ("Logged in Successfully as " + ad.getUsername());
		
	}
	

	@Override
	public Employee addEmployee(Employee employee) throws DuplicateEmployeeException, UserNotLoggedInException {
		if(loggedIn) {
			if(employeeDaoRepo.findByUsername(employee.getUserLoginDetails().getUsername()).isEmpty()) {
				employee.getUserLoginDetails().setUserType(UserType.EMPLOYEE);
				employee.setActive(1);
				return employeeDaoRepo.save(employee);
			}
			else
					throw new DuplicateEmployeeException("Employee Already Exists");
		}
		else
			throw new UserNotLoggedInException("Please Login First");
		
	}

	@Override
	public Employee modifyEmployee(int employeeId, Employee employee) throws NoSuchEmployeeException, UserNotLoggedInException{
		if(loggedIn) {
			
			Employee employeetoUpdate = employeeDaoRepo.findById(employeeId).orElseThrow(() -> new NoSuchEmployeeException("Employee Details not found"));
			System.out.println(employeetoUpdate);
			employeetoUpdate.setName(employee.getName());
			employeetoUpdate.setEmail(employee.getEmail());
			employeetoUpdate.setPhone(employee.getPhone());
			employeetoUpdate.setAddress(employee.getAddress());
			
			return employeeDaoRepo.save(employeetoUpdate); 
			
		}
		else
			throw new UserNotLoggedInException("Please Login First");
		
	}

	@Override
	public boolean removeEmployee(String username) throws NoSuchEmployeeException, UserNotLoggedInException {
		if(loggedIn) {
			Optional<Employee> emp = employeeDaoRepo.findByUsername(username);
			
			if(emp.isEmpty())
				throw new NoSuchEmployeeException("Employee Details not found");
			else {
				emp.get().setActive(0);
				employeeDaoRepo.save(emp.get());
				return true;
			}
		}
		else
			throw new UserNotLoggedInException("Please Login First");
		
		
	}

	@Override
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException, UserNotLoggedInException {
		if(loggedIn) 
			return employeeDaoRepo.findByEmployeeId(employeeId).orElseThrow(()-> new NoSuchEmployeeException("Employee Details not found"));
		else
			return null;
	}

	@Override
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException, UserNotLoggedInException {
		if(loggedIn) {
			List<Employee> elist = (List<Employee>)employeeDaoRepo.findByName(name);
			if(elist.isEmpty())
				throw new NoSuchEmployeeException("Employee Details not found"); 
			else
				return elist;
		}
		else
			throw new UserNotLoggedInException("Please Login First");
	}

	@Override
	public List<Employee> findAllEmployee() throws NoSuchEmployeeException, UserNotLoggedInException {
		if(loggedIn) {
			List<Employee> elist = (List<Employee>)employeeDaoRepo.findAllActiveEmployee();
			if(elist.isEmpty())
				throw new NoSuchEmployeeException("Employee Details not found"); 
			else
				return elist;
		
		}
		else
			throw new UserNotLoggedInException("Please Login First");
	}
	
	@Override
	public List<DonationDistribution> findAllPendingDonations() throws UserNotLoggedInException{
		if(loggedIn) {
			
			List<DonationDistribution> dlist = (List<DonationDistribution>) donationdistributionDaoRepo.findByStatus(DonationDistributionStatus.PENDING);
			return dlist;
		}
		else
			throw new UserNotLoggedInException("Please Login First");
	}
	@Override
	public DonationDistribution approveDonation(DonationDistribution distribution) throws UserNotLoggedInException{
		if(loggedIn) {
			Request request= requestRepo.findById(distribution.getRequestId()).get();
			
			requestRepo.save(request);
			DonationDistribution dd = donationdistributionDaoRepo.findById(distribution.getId()).get();
			dd.setStatus(distribution.getStatus());
			dd.setApprovalOrRejectedDate(new Date());
			
			if(distribution.getStatus() == DonationDistributionStatus.APPROVED) {
				dd.setNgo(distribution.getNgo());
				request.setStatus(RequestStatus.APPROVED_BY_ADMIN);
			}
			else
				request.setStatus(RequestStatus.REJECTED_BY_ADMIN);
			
			
			return donationdistributionDaoRepo.save(dd);
		}
		else
			throw new UserNotLoggedInException("Please Login First");
	}
	
	
	@Override
	public DonationBox addNgo(DonationBox newNgo) throws UserNotLoggedInException{
		if(loggedIn) {
			newNgo.setNgoName(newNgo.getNgoName().toUpperCase());
			return donationBoxRepo.save(newNgo);
		}
		else
			throw new UserNotLoggedInException("Please Login First");
	}
	
	
	@Override
	public List<DonationBox> findAllNgo() throws UserNotLoggedInException{
		if(loggedIn) {
			List<DonationBox> dlist = (List<DonationBox>) donationBoxRepo.findAll();
			return dlist;
		}
		else
			throw new UserNotLoggedInException("Please Login First");
	}
	
	@Override
	public String logout() {
		if(loggedIn == true) {
			loggedIn = false;
			return "Logged Out Successfully";
		}
		else
			return "Could not Logout";
	}

}
