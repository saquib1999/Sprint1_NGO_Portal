package com.cg.ngoportal.dao;

import java.sql.SQLException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.Request;
@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer>{
	
	
	public int login(String username, String password) throws SQLException, NoSuchEmployeeException;

	public String helpNeedyPerson(DonationDistribution distribute);
	public DonationDistribution approveDonationEmployeeLevel(Request request);
	public boolean logout();
}
