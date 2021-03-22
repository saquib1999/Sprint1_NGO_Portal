package com.cg.ngoportal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.ngoportal.dao.DonationDistributionDao;
import com.cg.ngoportal.exception.DuplicateEmployeeException;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.InvalidNeedyPersonObjectException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.Employee;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;

@Service
public interface EmployeeService {
	public boolean login(String username,String password)throws NoSuchEmployeeException;
	public NeedyPeople addNeedyPerson(NeedyPeople person) throws  UserNotLoggedInException, DuplicateNeedyPersonException, InvalidNeedyPersonObjectException;
	public NeedyPeople removeNeedyPerson(NeedyPeople person) throws UserNotLoggedInException, NoSuchNeedyPersonException;
	public NeedyPeople findNeedyPeopleById(int id) throws UserNotLoggedInException, NoSuchNeedyPersonException;
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws UserNotLoggedInException;
	public List<NeedyPeople> findAllNeedyPeople() throws UserNotLoggedInException;
	public DonationDistribution helpNeedyPerson(DonationDistribution distribute) throws UserNotLoggedInException;
	public DonationDistribution approveDonationDistributionEmployeeLevel(Request request, DonationDistribution distribution) throws UserNotLoggedInException;
	public List<DonationDistribution> checkApprovedDistribution() throws UserNotLoggedInException;
	public List<Request> checkPendingRequests() throws UserNotLoggedInException;
	public boolean logOut();
}
