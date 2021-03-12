package com.cg.ngoportal.service;

import com.cg.ngoportal.model.NeedyPeople;

public interface NeedyPeopleService {
	public boolean registerNeedyPerson(NeedyPeople person);
	public boolean login(NeedyPeople person);
	public boolean requestForHelp(NeedyPeople person);
	
}
