package com.cg.ngoportal.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.ngoportal.controller.LoginCred;
import com.cg.ngoportal.dao.NeedyPeopleDao;
import com.cg.ngoportal.dao.RequestDao;
import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.NoSuchEmployeeException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.User;
import com.cg.ngoportal.model.UserType;

@Service
public class NeedyPeopleServiceImpl implements NeedyPeopleService {

	private boolean loggedIn = true;
//	private int needyPersonId = 1000;

	@Autowired
	NeedyPeopleDao needyPeopleRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	RequestDao requestRepo;

	@Autowired
	UserDao userRepo;

	// Register Needy Person
	@Override
	public NeedyPeople register(NeedyPeople person) throws DuplicateNeedyPersonException {
		Optional<User> user = userRepo.findByUsername(person.getUserLoginDetails().getUsername());
		if (user.isEmpty()) {

			User user2 = person.getUserLoginDetails();
			user2.setPassword(bcryptEncoder.encode(user2.getPassword()));
			person.setUserLoginDetails(user2);
			person.getUserLoginDetails().setUserType(UserType.NEEDYPERSON);
			needyPeopleRepo.save(person);
			return person;
		} else
			throw new DuplicateNeedyPersonException("Username is already taken.");

	}

	// Login Needy Person
	@Override
	public String login(User user) throws NoSuchNeedyPersonException {

		loggedIn = true;
//		needyPersonId = needyPeopleRepo.findByUserLoginDetails(user).get().getId();
		return "Logged In as " + user.getUsername();

	}

	// Needy Person requests for help
	@Override
	public Request requestForHelp(Request request, int needyPersonId) throws UserNotLoggedInException {
		request.setNeedyPersonId(needyPersonId);
		return requestRepo.save(request);

	}

	// Check the request status
	@Override
	public List<Request> requestStatusCheck(int needyPersonId) throws UserNotLoggedInException {
		List<Request> request = requestRepo.findByNeedyPersonId(needyPersonId);
		return request;

	}

	// Modify Needy Person details
	@Override
	public NeedyPeople modifyNeedyPerson(NeedyPeople needyPerson, int needyPersonId)
			throws NoSuchNeedyPersonException, UserNotLoggedInException {
		NeedyPeople persontoUpdate = needyPeopleRepo.findById(needyPersonId).get();
		persontoUpdate.setName(needyPerson.getName());
		persontoUpdate.setPhone(needyPerson.getPhone());
		persontoUpdate.setFamilyIncome(needyPerson.getFamilyIncome());
		persontoUpdate.setAddress(needyPerson.getAddress());
		return needyPeopleRepo.save(persontoUpdate);

	}

	// Get Needy Person By ID
	public NeedyPeople getNeedyPersonById(int needyPersonId) throws NoSuchNeedyPersonException, UserNotLoggedInException {
		return needyPeopleRepo.findById(needyPersonId)
				.orElseThrow(()-> new NoSuchNeedyPersonException("Details not found"));
	}

	// Logout Needy Person
	@Override
	public String logOut() {
		loggedIn = false;
//		needyPersonId = -1;
		return "Logged Out!";

	}

}