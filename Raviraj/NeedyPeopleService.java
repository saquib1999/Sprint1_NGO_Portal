package com.cg.ngoportal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ngoportal.exception.DuplicateNeedyPersonException;
import com.cg.ngoportal.exception.NoSuchNeedyPersonException;
import com.cg.ngoportal.exception.UserNotLoggedInException;
import com.cg.ngoportal.model.NeedyPeople;
import com.cg.ngoportal.model.Request;

@Service
public interface NeedyPeopleService {
	
	public NeedyPeople register(NeedyPeople person) throws DuplicateNeedyPersonException;
	public String loginNeedyPerson(String username, String password) throws NoSuchNeedyPersonException;
	public Request requestForHelp(Request request) throws UserNotLoggedInException;
	public List<Request> requestStatusCheck() throws UserNotLoggedInException;
	public String logOut();
	
	
	
}
