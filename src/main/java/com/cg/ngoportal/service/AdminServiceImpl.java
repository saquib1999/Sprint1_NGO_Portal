package com.cg.ngoportal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.dao.AdminDao;
import com.cg.ngoportal.dao.DonationBoxDao;
import com.cg.ngoportal.dao.DonationDistributionDao;
import com.cg.ngoportal.dao.EmployeeDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.Admin;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.DonationDistributionStatus;
import com.cg.ngoportal.model.Employee;

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
	private boolean loggedIn = true;
	
	@Override
	public boolean login(Admin admin){
		// TODO Auto-generated method stub
		//System.out.println(admin.getUsername());
		//System.out.println(admin.getPassword());
		//adminDaoRepo.save(admin);
		Admin ad = adminDaoRepo.findByUsernameAndPassword(admin.getUsername(), admin.getPassword()).orElseThrow();
		loggedIn = true;
		return true;
		/*
		Admin ad = adminDaoRepo.findByUsername(admin.getAdminUsername()).orElse(null);
		if(ad.getAdminPassword() == admin.getAdminPassword())
			loggedIn = true;
		else
			return false;*/
		
	}
	

	@Override
	public Employee addEmployee(Employee employee) throws DuplicateEmployeeException {
		// TODO Auto-generated method stub
		if(loggedIn) {
			if(employeeDaoRepo.findByUsername(employee.getUserLoginDetails().getUsername()).isEmpty()) {
				employee.setActive(1);
				return employeeDaoRepo.save(employee);
			}
			else
					throw new DuplicateEmployeeException("Employee Already Exists");
		}
		else
			return null;
		
	}

	@Override
	public Employee modifyEmployee(int employeeId, Employee employee) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		if(loggedIn) {
			
			Employee employeetoUpdate = employeeDaoRepo.findById(employeeId).orElseThrow(() -> new NoSuchEmployeeException("Employee Details not found"));
			
			employeetoUpdate.setName(employee.getName());
			employeetoUpdate.setEmail(employee.getEmail());
			employeetoUpdate.setPhone(employee.getPhone());
			employeetoUpdate.setAddress(employee.getAddress());
			//employeetoUpdate.setUserLoginDetails(employee.getUserLoginDetails());
			
			return employeeDaoRepo.save(employeetoUpdate); 
			
		}
		else
			return null;
		
	}

	@Override
	public boolean removeEmployee(String username) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
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
			return false;
		
		
	}

	@Override
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		if(loggedIn) 
			return employeeDaoRepo.findByEmployeeId(employeeId).orElseThrow(()-> new NoSuchEmployeeException("Employee Details not found"));
		else
			return null;
	}

	@Override
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		if(loggedIn) {
			List<Employee> elist = (List<Employee>)employeeDaoRepo.findByName(name);
			if(elist.isEmpty())
				throw new NoSuchEmployeeException("Employee Details not found"); 
			else
				return elist;
		}
		else
			return null;
	}

	@Override
	public List<Employee> findAllEmployee() throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		if(loggedIn) {
			List<Employee> elist = (List<Employee>)employeeDaoRepo.findAllActiveEmployee();
			if(elist.isEmpty())
				throw new NoSuchEmployeeException("Employee Details not found"); 
			else
				return elist;
		
		}
		else
			return null;
	}
	
	@Override
	public List<DonationDistribution> findAllPendingDonations(){
		if(loggedIn) {
			List<DonationDistribution> dlist = (List<DonationDistribution>) donationdistributionDaoRepo.findAllPendingDonations();
			return dlist;
		}
		else
			return null;
	}
	@Override
	public DonationDistribution approveDonation(DonationDistribution distribution) {
		// TODO Auto-generated method stub
		if(loggedIn) {
			//distribution.setStatus(DonationDistributionStatus.APPROVED);
			DonationDistribution dd = donationdistributionDaoRepo.findById(distribution.getId()).get();
			dd.setStatus(DonationDistributionStatus.APPROVED);
			dd.setApprovalOrRejectedDate(LocalDate.now());
			return donationdistributionDaoRepo.save(distribution);
		}
		else
			return null;
	}
	
	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		if(loggedIn == true) {
			loggedIn = false;
			return true;
		}
		else
			return false;
	}

}
