package com.cg.ngoportal.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.InvalidNeedyPersonObjectException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.DonationDistribution;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.User;

@Service
public interface EmployeeService {
	public String login(User user)throws NoSuchEmployeeException;
	public NeedyPeople addNeedyPerson(NeedyPeople person) throws  UserNotLoggedInException, DuplicateNeedyPersonException, InvalidNeedyPersonObjectException;
	public NeedyPeople removeNeedyPerson(int id) throws UserNotLoggedInException, NoSuchNeedyPersonException;
	public NeedyPeople findNeedyPeopleById(int id) throws UserNotLoggedInException, NoSuchNeedyPersonException;
	public List<NeedyPeople> findNeedyPeopleByName(String name) throws UserNotLoggedInException;
	public List<NeedyPeople> findAllNeedyPeople() throws UserNotLoggedInException;
	public DonationDistribution helpNeedyPerson(DonationDistribution distribute) throws UserNotLoggedInException;
	public DonationDistribution approveDonationDistributionEmployeeLevel(int employeeId,Request request, DonationDistribution distribution) throws UserNotLoggedInException;
	public List<DonationDistribution> checkApprovedDistribution(int employeeId) throws UserNotLoggedInException;
	public List<Request> checkPendingRequests() throws UserNotLoggedInException;
	public List<DonationDistribution> checkDistributedList(int employeeId) throws UserNotLoggedInException;
	public boolean logOut();
	public List<DonationDistribution> checkPendingList(int employeeId) throws UserNotLoggedInException;

	
}
