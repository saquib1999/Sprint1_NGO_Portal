package com.cg.ngoportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;

@Service
public interface EmployeeService {
	public boolean login(String username,String password)throws NoSuchEmployeeException;
	public NeedyPeople addNeedyPerson(NeedyPeople person);
	public NeedyPeople removeNeedyPerson(NeedyPeople person);
	public Optional<NeedyPeople> findNeedyPeopleById(int id);
	public List<NeedyPeople> findNeedyPeopleByName(String name);
	public List<NeedyPeople> findAllNeedyPeople();
	public String helpNeedyPerson(DonationDistribution distribute);
	public boolean logout();
}
