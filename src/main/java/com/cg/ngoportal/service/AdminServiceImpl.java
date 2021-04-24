package com.cg.ngoportal.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDao adminDaoRepo;
	
	@Autowired
	EmployeeDao employeeDaoRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	
	@Autowired
	DonationDistributionDao donationdistributionDaoRepo;
	
	@Autowired
	UserDao userDaoRepo;
	private boolean loggedIn = true;
	
	@Autowired
	DonationBoxDao donationBoxRepo;
	
	@Autowired
	RequestDao requestRepo;
	
	@Override
	public String login(Admin admin) throws IncorrectUsernameOrPasswordException{
			return ("Logged in Successfully as ");
		
	}
	

	@Override
	public Employee addEmployee(Employee employee) throws DuplicateEmployeeException, UserNotLoggedInException {
		// TODO Auto-generated method stub
		
			if(employeeDaoRepo.findByUsername(employee.getUserLoginDetails().getUsername()).isEmpty()) {
				User user = employee.getUserLoginDetails();
				user.setPassword(bcryptEncoder.encode(user.getPassword()));
				employee.setUserLoginDetails(user);
				
				employee.getUserLoginDetails().setUserType(UserType.EMPLOYEE);
				employee.setActive(1);
				return employeeDaoRepo.save(employee);
			}
			else
					throw new DuplicateEmployeeException("Employee Already Exists");
		
		
	}

	@Override
	public Employee modifyEmployee(int employeeId, Employee employee) throws NoSuchEmployeeException, UserNotLoggedInException{
		// TODO Auto-generated method stub
		
			
			Employee employeetoUpdate = employeeDaoRepo.findById(employeeId).orElseThrow(() -> new NoSuchEmployeeException("Employee Details not found"));
			System.out.println(employeetoUpdate);
			employeetoUpdate.setName(employee.getName());
			employeetoUpdate.setEmail(employee.getEmail());
			employeetoUpdate.setPhone(employee.getPhone());
			employeetoUpdate.setAddress(employee.getAddress());
			//employeetoUpdate.setUserLoginDetails(employee.getUserLoginDetails());
			
			return employeeDaoRepo.save(employeetoUpdate); 
			
		
		
	}

	@Override
	public boolean removeEmployee(String username) throws NoSuchEmployeeException, UserNotLoggedInException {
		// TODO Auto-generated method stub
			Optional<Employee> emp = employeeDaoRepo.findByUsername(username);
			
			if(emp.isEmpty())
				throw new NoSuchEmployeeException("Employee Details not found");
			else {
				emp.get().setActive(0);
				employeeDaoRepo.save(emp.get());
				return true;
			}
		
	}

	@Override
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException, UserNotLoggedInException {
		// TODO Auto-generated method stub
			return employeeDaoRepo.findByEmployeeId(employeeId).orElseThrow(()-> new NoSuchEmployeeException("Employee Details not found"));
		
	}

	@Override
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException, UserNotLoggedInException {
		// TODO Auto-generated method stub
			List<Employee> elist = (List<Employee>)employeeDaoRepo.findByName(name);
			if(elist.isEmpty())
				throw new NoSuchEmployeeException("Employee Details not found"); 
			else
				return elist;
		
	}

	@Override
	public List<Employee> findAllEmployee() throws NoSuchEmployeeException, UserNotLoggedInException {
		// TODO Auto-generated method stub
			List<Employee> elist = (List<Employee>)employeeDaoRepo.findAllActiveEmployee();
			if(elist.isEmpty())
				throw new NoSuchEmployeeException("Employee Details not found"); 
			else
				return elist;
		
		
	}
	
	@Override
	public List<DonationDistribution> findAllPendingDonations() throws UserNotLoggedInException{
		
			
			
			List<DonationDistribution> dlist = (List<DonationDistribution>) donationdistributionDaoRepo.findByStatus(DonationDistributionStatus.PENDING);
			return dlist;
		
		
	}
	@Override
	public DonationDistribution approveDonation(DonationDistribution distribution) throws UserNotLoggedInException{
		
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
	
	
	@Override
	public DonationBox addNgo(DonationBox newNgo) throws UserNotLoggedInException{
			newNgo.setNgoName(newNgo.getNgoName().toUpperCase());
			return donationBoxRepo.save(newNgo);
		
	}
	
	
	@Override
	public List<DonationBox> findAllNgo() throws UserNotLoggedInException{
			List<DonationBox> dlist = (List<DonationBox>) donationBoxRepo.findAll();
			return dlist;
		
	}
	
	@Override
	public String logout() {
		
			loggedIn = false;
			return "Logged Out Successfully";
		
	}


	@Override
	public Admin addUser(Admin admin) {

		return adminDaoRepo.save(admin);	

	}

}
