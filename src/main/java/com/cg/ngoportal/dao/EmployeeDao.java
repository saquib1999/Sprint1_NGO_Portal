package com.cg.ngoportal.dao;

import java.sql.SQLException;
import java.util.List;

import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;

public interface EmployeeDao {
	public int login(String username, String password) throws SQLException, NoSuchEmployeeException;
	public int createNeedyPerson(NeedyPeople person);
	public int deleteNeedyPerson(NeedyPeople person);
	public NeedyPeople readNeedyPeopleById(int id);
	public List<NeedyPeople> readNeedyPeopleByName(String name);
	public List<NeedyPeople> readAllNeedyPeople();
	public String helpNeedyPerson(DonationDistribution distribute);
	public DonationDistribution approveDonationEmployeeLevel(Request request);
	public boolean logout();
}
