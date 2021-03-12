package com.cg.ngoportal.service;

import java.util.List;

import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;

public interface EmployeeService {
	public boolean login(String username,String password)throws NoSuchEmployeeException;
	public boolean addNeedyPerson(NeedyPeople person);
	public boolean removeNeedyPerson(NeedyPeople person);
	public NeedyPeople findNeedyPeopleById(int id);
	public List<NeedyPeople> findNeedyPeopleByName(String name);
	public List<NeedyPeople> findAllNeedyPeople();
	public String helpNeedyPerson(DonationDistribution distribute);
}
