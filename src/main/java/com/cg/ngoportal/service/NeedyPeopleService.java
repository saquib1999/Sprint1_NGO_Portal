package com.cg.ngoportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;
import com.cg.ngoportal.model.User;

@Service
public interface NeedyPeopleService {
	
	public NeedyPeople register(NeedyPeople person) throws DuplicateNeedyPersonException;
	public String login(User user) throws NoSuchNeedyPersonException;
	public Request requestForHelp(Request request) throws UserNotLoggedInException;
	public List<Request> requestStatusCheck() throws UserNotLoggedInException;
	public String logOut();
	public NeedyPeople modifyNeedyPerson(NeedyPeople needyPerson) throws NoSuchNeedyPersonException, UserNotLoggedInException;
	 
	
	
}
