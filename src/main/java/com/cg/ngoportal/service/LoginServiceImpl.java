package com.cg.ngoportal.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cg.ngoportal.dao.UserDao;
import com.cg.ngoportal.exception.IncorrectUsernameOrPasswordException;
import com.cg.ngoportal.model.User;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	UserDao userRepo;
	
	@Autowired
	private NeedyPeopleService needyPeopleService;
	
	@Autowired
	private DonorService donorService;
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public String login(User loginCred) {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsernameAndPassword(loginCred.getUsername(), loginCred.getPassword())
				.orElseThrow(()->new IncorrectUsernameOrPasswordException("Check Username or Password or Usertype"));
		
		if(user.getUserType() != loginCred.getUserType())
			throw new IncorrectUsernameOrPasswordException("Check Username or Password or Usertype");
		
		
		switch(loginCred.getUserType().ordinal()) {
		
		case 0:	return employeeService.login(user);
			
		case 1:	return donorService.login(user);	
			
		case 2:	return needyPeopleService.login(user);
				
		default : 
		
			return null;
		}

	}
}
