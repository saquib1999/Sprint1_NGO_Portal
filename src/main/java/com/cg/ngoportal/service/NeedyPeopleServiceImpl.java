package com.cg.ngoportal.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.ngoportal.dao.NeedyPeopleDao;
import com.cg.ngoportal.dao.RequestDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.User;

@Service
public class NeedyPeopleServiceImpl implements NeedyPeopleService {

	private boolean loggedIn = true;
	private int needyPersonId = -1;

	@Autowired
	NeedyPeopleDao needyPeopleRepo;

	@Autowired
	RequestDao requestRepo;

	@Autowired
	UserDao userRepo;

	//Register Needy Person
	@Override
	public NeedyPeople register(NeedyPeople person) throws DuplicateNeedyPersonException {
		Optional<User> user = userRepo.findByUsername(person.getUserLoginDetails().getUsername());
		if(user.isEmpty()) {
			needyPeopleRepo.save(person);
		}
		else {
			throw new DuplicateNeedyPersonException("Username is already taken.");
		}
		return person;
		
	}
	
	//Login Needy Person
	@Override
	public String loginNeedyPerson(String username, String password) throws NoSuchNeedyPersonException{
		User user = userRepo.findByUsernameAndPassword(username, password)
				.orElseThrow(()->new NoSuchNeedyPersonException("Username or Password is incorrect."));
		
		loggedIn = true; 
		needyPersonId = needyPeopleRepo.findByUserLoginDetails(user).get().getId();
		return /* "Logged In as " */ user.getUsername();
		
	}

	//Needy Person requests for help 
	@Override
	public Request requestForHelp(Request request) throws UserNotLoggedInException{
		if(loggedIn) 
			return requestRepo.save(request);
		else
			throw new UserNotLoggedInException("Please Log in.");
		
	}

	//Check the request status
	@Override
	public List<Request> requestStatusCheck() throws UserNotLoggedInException {
		if(loggedIn) {
			List<Request> request = requestRepo.findByNeedyPersonId(needyPersonId);
			return request; 
		}
		else 
			throw new UserNotLoggedInException("Please Log in.");
	}
		
	//Check the request status  (For testing)
	@Override
	public List<Request> requestStatusCheck(int needyPersonId) throws UserNotLoggedInException{
		if(loggedIn) {
			List<Request> request = requestRepo.findByNeedyPersonId(needyPersonId);
			return request; 
		}
		else 
			throw new UserNotLoggedInException("Please Log in.");
		
	}

	//Logout Needy Person
	@Override
	public String logOut() 
	{
		loggedIn = false;
		needyPersonId = -1;
		return "Logged Out!";
		
	}

	
}
