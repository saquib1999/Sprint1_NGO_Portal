package com.cg.ngoportal.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.ngoportal.dao.AdminDaoImpl;
import com.cg.ngoportal.dao.IAdminRepository;
import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private IAdminRepository repo;
	
	@Override
	public boolean login(String username, String password)throws SQLException{
		int flag = repo.login(username, password);
		if(flag == 1)
			return true;
		else
			return false;
	}
	@Override
	public boolean addEmployee(Employee employee) throws DuplicateEmployeeException {
		// TODO Auto-generated method stub
		int flag = repo.createEmployee(employee);
		if(flag == 1)
			return true;
		else
			return false;
		
		//if(emp.getEmployeeId() == employee.getEmployeeId())
	}

	@Override
	public Employee modifyEmployee(Employee employee) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		Employee emp = repo.updateEmployee(employee);
		
		if(emp.getEmployeeId() == employee.getEmployeeId())
			return emp;
		else
			return null;
	}

	@Override
	public boolean removeEmployee(int employeeId) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		int flag = repo.deleteEmployee(employeeId);
		if(flag == 1)
			return true;
		else
			return false;
	}

	@Override
	public Employee findEmployeeById(int employeeId) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		Employee emp = repo.readEmployeeById(employeeId);
		
		if(emp.getEmployeeId() == employeeId)
			return emp;
		else
			return null;
	}

	@Override
	public List<Employee> findEmployeeByName(String name) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub\
		List<Employee> elist = new ArrayList<Employee>();
		
		elist = repo.readEmployeeByName(name);
		
		if(elist.size() != 0)
			return elist;
		else
			return null;
	}

	@Override
	public List<Employee> findAllEmployee() throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		List<Employee> elist = repo.readAllEmployees();
		
		if(elist.size() != 0)
			return elist;
		else
			return null;
	}

	@Override
	public boolean approveDonation(DonationDistribution distribution) {
		// TODO Auto-generated method stub
		if(repo.approveDonation(distribution))
			return true;
		else
			return false;
	}

}
