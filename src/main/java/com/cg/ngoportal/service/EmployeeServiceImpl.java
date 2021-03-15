package com.cg.ngoportal.service;

import java.util.List;

import com.cg.ngoportal.dao.EmployeeDaoImpl;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;

public class EmployeeServiceImpl implements EmployeeService {
	
	private static EmployeeDaoImpl repo = new EmployeeDaoImpl();

	@Override
	public boolean login(String username,String password) throws NoSuchEmployeeException {
		// TODO Auto-generated method stub
		
		try {
			if(repo.login(username,password)>0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean addNeedyPerson(NeedyPeople person) {
		// TODO Auto-generated method stub
		if (repo.createNeedyPerson(person)>0)
			return true;
		return false;
	}

	@Override
	public boolean removeNeedyPerson(NeedyPeople person) {
		// TODO Auto-generated method stub
		if(repo.deleteNeedyPerson(person)>0)
			return true;
		return false;
	}

	@Override
	public NeedyPeople findNeedyPeopleById(int id) {
		// TODO Auto-generated method stub
		return repo.readNeedyPeopleById(id);
	}

	@Override
	public List<NeedyPeople> findNeedyPeopleByName(String name) {
		// TODO Auto-generated method stub
		return repo.readNeedyPeopleByName(name);
	}

	@Override
	public List<NeedyPeople> findAllNeedyPeople() {
		// TODO Auto-generated method stub
		return repo.readAllNeedyPeople();
	}

	@Override
	public String helpNeedyPerson(DonationDistribution distribute) {
		// TODO Auto-generated method stub
		return repo.helpNeedyPerson(distribute);
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		return repo.logout();
	}

}
