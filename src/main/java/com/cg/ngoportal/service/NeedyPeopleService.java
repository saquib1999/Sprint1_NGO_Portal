package com.cg.ngoportal.service;

import org.springframework.stereotype.Service;

import com.cg.ngoportal.model.NeedyPeople;
@Service
public interface NeedyPeopleService {
	public boolean registerNeedyPerson(NeedyPeople person);
	public boolean login(NeedyPeople person);
	public boolean requestForHelp(NeedyPeople person);
	
}
